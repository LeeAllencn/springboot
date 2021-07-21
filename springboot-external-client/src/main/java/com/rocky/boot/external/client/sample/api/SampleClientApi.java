package com.rocky.boot.external.client.sample.api;

import com.rocky.boot.external.client.sample.model.response.Contributor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * SampleClientApi
 * @author : rocky
 * @date : created in 2021/6/17
 */
public interface SampleClientApi {

    /**
     * 获取contributors
     * @param owner String
     * @param repo String
     * @return List<Contributor>
     */
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
