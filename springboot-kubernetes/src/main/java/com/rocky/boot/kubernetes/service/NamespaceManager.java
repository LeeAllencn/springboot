package com.rocky.boot.kubernetes.service;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author rocky
 * Description: k8s namespace
 * Created in 2021/3/16
 */
@Slf4j
@Component
public class NamespaceManager {

    /**
     * 根据namespace名称查询namespace详情
     *
     * @param client k8s客户端
     * @param name   namespace名称
     * @return Namespace
     */
    public Namespace getNamespace(KubernetesClient client, String name) {
        return client.namespaces().withName(name).get();
    }
}
