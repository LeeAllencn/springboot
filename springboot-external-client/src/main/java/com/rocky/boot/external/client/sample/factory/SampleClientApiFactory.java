package com.rocky.boot.external.client.sample.factory;

import com.rocky.boot.common.client.OkHttpClientFactory;
import com.rocky.boot.external.client.sample.api.SampleClientApi;
import com.rocky.boot.external.client.sample.interceptor.TokenInterceptor;
import com.rocky.boot.external.client.utils.RetrofitUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author : rocky
 * @date : created in 2021/7/1
 */
public class SampleClientApiFactory {

    private final OkHttpClientFactory okHttpClientFactory;

    public SampleClientApiFactory(OkHttpClientFactory okHttpClientFactory) {
        this.okHttpClientFactory = okHttpClientFactory;
    }

    /**
     *
     * @param baseUrl url
     * @param token token
     * @return SampleClientApi
     */
    public SampleClientApi createSampleClientApi(String baseUrl, String token) {
        OkHttpClient client = okHttpClientFactory.newSslClient(new TokenInterceptor(token));
        Retrofit retrofit = RetrofitUtils.createRetrofit(client, baseUrl);
        return retrofit.create(SampleClientApi.class);
    }
}
