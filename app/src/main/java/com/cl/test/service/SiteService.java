package com.cl.test.service;

import com.cl.test.model.Site;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c.l on 17/08/2017.
 */

public interface SiteService {

    @GET("sample.json")
    Call<List<Site>> getSite();
}
