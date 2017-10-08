package com.cl.test.net;

import com.cl.test.util.LogUtil;

import rx.Subscriber;

/**
 * Created by c,l. on 6/10/2017.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    private int flag;

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i("Subscriber", "onStart");
    }

    @Override
    public void onCompleted() {
        LogUtil.i("Subscriber", "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.i("Subscriber", "onError :" + e.getMessage());
    }

    @Override
    public void onNext(T t) {
        LogUtil.i("Subscriber", "onNext");
    }

    public int getFlag() {
        return flag;
    }

    public BaseSubscriber<T> setFlag(int flag) {
        this.flag = flag;
        return this;
    }
}
