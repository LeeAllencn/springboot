package com.rocky.boot.flowable.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.concurrent.TimeUnit;

/**
 * @author : rocky
 * @date : created in 2023/4/18
 */
public class FeignClientUtil {

    private static OkHttpClient okHttpClient;
    private static Encoder feignEncoder = feignEncoder();
    private static Decoder feignDecoder = feignDecoder();
    private static SpringMvcContract springMvcContract = new SpringMvcContract();

    static {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.readTimeout(600, TimeUnit.SECONDS);
        builder.writeTimeout(600, TimeUnit.SECONDS);
        builder.connectTimeout(600, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        okHttpClient = new OkHttpClient(builder.build());
    }
    public static <T> T getRemoteService(String url, Class<T> clazz, long readTimeOut, long connectTimeout) {
        Request.Options options = new Request.Options(connectTimeout, TimeUnit.SECONDS, readTimeOut, TimeUnit.SECONDS, true);
        return Feign.builder().options(options).contract(springMvcContract)
                .client(okHttpClient)
                .encoder(feignEncoder)
                .decoder(feignDecoder)
                .retryer(Retryer.NEVER_RETRY)
                .target(clazz, url);
    }

    public static Encoder feignEncoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new SpringEncoder(objectFactory);
    }

    public static Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    private static ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // customize as much as you want
        return objectMapper;
    }
}
