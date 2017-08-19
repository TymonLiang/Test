package com.cl.test;

import android.app.Activity;
import android.app.Application;

import com.cl.test.util.CrashHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by c.l on 16/08/2017.
 */

public class MyApp extends Application {

    private List<Activity> activityList = null;
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        activityList = new LinkedList<>();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(), new CrashHandler.CrashCallback() {
            @Override
            public void OnCrash(Throwable e) {

            }
        });

    }

    public void addActivity(Activity activity){
        if(activityList != null){
            if(!activityList.contains(activity)){

            }
        }
    }
}
