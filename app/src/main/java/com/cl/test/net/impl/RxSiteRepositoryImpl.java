package com.cl.test.net.impl;

import com.cl.test.model.Site;
import com.cl.test.net.BaseSubscriber;
import com.cl.test.net.IRxSiteRepository;
import com.cl.test.net.NetManager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 6/10/2017.
 */

public class RxSiteRepositoryImpl implements IRxSiteRepository {

    @Override
    public void getSiteData(Subscriber<List<Site>> subscriber) {
        NetManager.getRxSiteService().getSite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public Subscription getSiteData(int flag, BaseSubscriber<List<Site>> subscriber) {
        return NetManager.getRxSiteService().getSite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber.setFlag(flag));
    }

    @Override
    public Observable<List<Site>> getSiteData() {
        return NetManager.getRxSiteService().getSite()
                .subscribeOn(Schedulers.io());
    }
}
