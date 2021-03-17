package com.rocky.boot.kubernetes.service;

import com.rocky.boot.kubernetes.enums.WorkloadType;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.batch.CronJob;
import io.fabric8.kubernetes.api.model.batch.Job;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/16
 */
@Slf4j
@Component
public class WorkloadManager {

    /**
     * 根据yaml文件创建工作负载
     * kubectl create -f xxx.yaml
     *
     * @param client       k8s客户端
     * @param workloadType 工作负载类型
     * @param is           InputStream
     * @param namespace    namespace
     */
    public void createWorkloadByYaml(KubernetesClient client, WorkloadType workloadType, InputStream is, String namespace) {

        switch (workloadType) {
            case Deployment:
                Deployment deployment = client.apps().deployments().load(is).get();
                client.apps().deployments().inNamespace(namespace).create(deployment);
                break;
            case StatefulSet:
                StatefulSet statefulSet = client.apps().statefulSets().load(is).get();
                client.apps().statefulSets().inNamespace(namespace).create(statefulSet);
                break;
            case DaemonSet:
                DaemonSet daemonSet = client.apps().daemonSets().load(is).get();
                client.apps().daemonSets().inNamespace(namespace).create(daemonSet);
                break;
            case Job:
                Job job = client.batch().jobs().load(is).get();
                client.batch().jobs().inNamespace(namespace).create(job);
                break;
            case CronJob:
                CronJob cronJob = client.batch().cronjobs().load(is).get();
                client.batch().cronjobs().inNamespace(namespace).create(cronJob);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + workloadType);
        }
    }
}
