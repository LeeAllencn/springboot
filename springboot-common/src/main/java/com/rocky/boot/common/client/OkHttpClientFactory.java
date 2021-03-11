package com.rocky.boot.common.client;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * @author rocky
 * Description: OkHttpClientFactory
 * Created in 2021/3/11
 */
@Component
public class OkHttpClientFactory {

    @Autowired
    private OkHttpClient okHttpClient;

    /**
     * 新建一个客户端Builder
     *
     * @return
     */
    public OkHttpClient.Builder newBuilder() {
        return okHttpClient.newBuilder();
    }

    /**
     * 新建一个客户端
     *
     * @return
     */
    public OkHttpClient newClient() {
        return okHttpClient.newBuilder().build();
    }

    /**
     * 新建一个SSL客户端
     *
     * @param interceptors
     * @return
     */
    public OkHttpClient newSslClient(Interceptor... interceptors) {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HostnameVerifier hostnameVerifier = (s, sslSession) -> true;

        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        if (interceptors != null) {
            builder.interceptors().addAll(Arrays.asList(interceptors));
        }
        OkHttpClient okHttpClient = builder.sslSocketFactory(sslContext.getSocketFactory(), xtm)
                .hostnameVerifier(hostnameVerifier)
                .build();
        return okHttpClient;
    }
}
