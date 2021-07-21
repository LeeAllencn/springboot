package com.rocky.boot.external.client.sample.service;

import com.rocky.boot.external.client.sample.model.response.Contributor;

import java.util.List;

/**
 * @author : rocky
 * @date : created in 2021/7/1
 */
public interface SampleClientService {

    /**
     * 查询contributors信息
     * @param owner owner
     * @param repo repo
     * @return List<Contributor>
     */
    List<Contributor> contributors(String owner, String repo);
}
