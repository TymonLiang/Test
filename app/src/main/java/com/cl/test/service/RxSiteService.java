package com.cl.test.service;

import com.cl.test.model.Site;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by c.l. on 6/10/2017.
 */

public interface RxSiteService {

    @GET("sample.json")
    Observable<List<Site>> getSite();
}
