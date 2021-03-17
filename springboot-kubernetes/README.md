https://kubernetes.io/docs/reference/using-api/client-libraries/

https://github.com/fabric8io/kubernetes-client

https://www.hi-linux.com/posts/21466.html


# 测试代码示例

```java
@Slf4j
@SpringBootTest
class NamespaceManagerTest {

    private static final Config CONFIG = new ConfigBuilder()
            .withMasterUrl("https://127.0.0.1:6443")
            .withClientKeyData("${ClientKeyData}")
            .withClientCertData("${ClientCertData}")
            .withCaCertData("${CaCertData}")
            .build();

    @Resource
    private NamespaceManager namespaceManager;

    @Test
    void getNamespace() {
        KubernetesClient client = new DefaultKubernetesClient(CONFIG);
        String name = "";
        // 调用方代码
        try {
            Namespace namespace = namespaceManager.getNamespace(client, name);
            log.info(namespace.toString());
        } catch (Exception e) {
            log.error("获取namespace异常！masterUrl={}, name={}", client.getMasterUrl(), name, e);
            // 在调用方定义一个基础服务异常替换e，由全局异常处理，输出e.getMessage()信息
            throw e;
        }
    }
}
```