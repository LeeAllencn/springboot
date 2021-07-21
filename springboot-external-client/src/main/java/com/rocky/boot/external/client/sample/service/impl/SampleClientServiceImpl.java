package com.rocky.boot.external.client.sample.service.impl;

import com.rocky.boot.common.client.OkHttpClientFactory;
import com.rocky.boot.external.client.exceptions.ExternalServiceException;
import com.rocky.boot.external.client.sample.api.SampleClientApi;
import com.rocky.boot.external.client.sample.factory.SampleClientApiFactory;
import com.rocky.boot.external.client.sample.model.response.Contributor;
import com.rocky.boot.external.client.sample.service.SampleClientService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author : rocky
 * @date : created in 2021/7/1
 */
public class SampleClientServiceImpl implements SampleClientService {

    private final SampleClientApi sampleClientApi;

    public SampleClientServiceImpl(OkHttpClientFactory okHttpClientFactory, String baseUrl, String token) {
        SampleClientApiFactory sampleClientApiFactory = new SampleClientApiFactory(okHttpClientFactory);
        this.sampleClientApi = sampleClientApiFactory.createSampleClientApi(baseUrl, token);
    }

    @Override
    public List<Contributor> contributors(String owner, String repo) {
        Call<List<Contributor>> call = sampleClientApi.contributors(owner, repo);
        try {
            Response<List<Contributor>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new ExternalServiceException("请求外部Sample服务失败：" + response.code() + "," + response.message());
            }
        } catch (IOException e) {
            throw new ExternalServiceException("请求外部Sample服务异常：", e);
        }
    }
}
