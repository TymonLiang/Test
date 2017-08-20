package com.cl.test.net;

import com.cl.test.service.SiteService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c.l on 17/08/2017.
 * net tool
 */

public class NetManager {
    private static String base_path = "http://express-it.optusnet.com.au/";

    private static NetManager myInstance = new NetManager();

    public static NetManager getInstance() {
        return myInstance;
    }

    private SiteService siteService;

    private NetManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_path)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        siteService = retrofit.create(SiteService.class);
    }

    public SiteService getSiteService(){
        return siteService;
    }
}

