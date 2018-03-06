package com.rocky.boot.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Rocky on 2018-03-01.
 */
public class ESClient {

    private static String elasticUrl = "localhost";

    public static void main(String[] args) throws IOException {

        // 1. TransportClient 连接
//        TransportClient client = initTransportClient();

        // 2. RestClient 连接
//        RestHighLevelClient client = initRestHighLevelClient();
    }

    /**
     * 初始化TransportClient
     * @return
     */
    public static TransportClient initTransportClient() {
        // 连接设置
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        //add transport address
        InetSocketAddress address = new InetSocketAddress(elasticUrl, 9300);
        client.addTransportAddress(new InetSocketTransportAddress(address));
        return client;
    }

    /**
     * 初始化RestHighLevelClient
     * @return
     */
    public static RestHighLevelClient initRestHighLevelClient() {
        // 初始化rest client
        RestClient restClient = RestClient.builder(new HttpHost(elasticUrl, 9200, "http")).build();
        // 将RestClient转换为RestHighLevelClient
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClient);
        return restHighLevelClient;
    }
}
