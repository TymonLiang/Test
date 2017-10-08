package com.cl.test.net;

import com.cl.test.model.Site;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by c.l. on 6/10/2017.
 */

public interface IRxSiteRepository {
    void getSiteData(Subscriber<List<Site>> subscriber);

    Subscription getSiteData(int flag, BaseSubscriber<List<Site>> subscriber);

    Observable<List<Site>> getSiteData();
}
