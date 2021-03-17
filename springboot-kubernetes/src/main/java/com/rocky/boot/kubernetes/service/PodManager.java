package com.rocky.boot.kubernetes.service;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/16
 */
@Slf4j
@Component
public class PodManager {

    private static final CountDownLatch EXEC_LATCH = new CountDownLatch(1);

    /**
     * kubectl exec my-pod -- ls /
     *
     * @param client    k8s客户端
     * @param namespace namespace
     * @param name      pod名称
     * @param cmd       脚本命令："ls", "/"
     * @return ByteArrayOutputStream
     */
    public ByteArrayOutputStream execCmd(KubernetesClient client, String namespace, String name, String cmd) {

        ExecListener execListener = new ExecListener() {
            @Override
            public void onOpen(Response response) {
                log.info("Shell was opened");
            }

            @Override
            public void onFailure(Throwable throwable, Response response) {
                log.info("Some error encountered");
                EXEC_LATCH.countDown();
            }

            @Override
            public void onClose(int i, String s) {
                log.info("Shell Closing");
                EXEC_LATCH.countDown();
            }
        };

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream error = new ByteArrayOutputStream();

        ExecWatch execWatch = client.pods().inNamespace(namespace).withName(name)
                .writingOutput(out)
                .writingError(error)
                .usingListener(execListener)
                .exec(cmd);

        try {
            boolean latchTerminationStatus = EXEC_LATCH.await(5, TimeUnit.SECONDS);
            if (!latchTerminationStatus) {
                log.info("Latch could not terminate within specified time");
            }
            log.info("Exec Output: \n" + out);
            execWatch.close();
            return out;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return error;
        }
    }
}
