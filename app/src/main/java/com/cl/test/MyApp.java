package com.cl.test;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.cl.test.constants.Constants;
import com.cl.test.util.CrashHandler;
import com.cl.test.util.LogUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by c.l on 16/08/2017.
 * base application
 */

public class MyApp extends Application {

    //manage activities
    private static List<Activity> activityList = new LinkedList<>();
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        registerActivityListener();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(), new CrashHandler.CrashCallback() {
            @Override
            public void OnCrash(Throwable e) {

            }
        });

    }

    /**
     * @param activity add an activity to the list
     */
    public void addActivity(Activity activity){
        if(activityList != null){
            if(!activityList.contains(activity)){
                activityList.add(activity);
                LogUtil.d(Constants.TAG, "activityList:size:"+activityList.size());
            }
        }
    }

    /**
     * @param activity  delete an activity from the list
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
        LogUtil.d(Constants.TAG, "activityList:size:"+activityList.size());
    }

    /**
     * end all activities
     */
    public static void finishAllActivity() {
        if (activityList == null) {
            return;
        }
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    /**
     * exit app
     */
    public static void appExit() {
        try {
            LogUtil.e(Constants.TAG, "app exit");
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerActivityListener() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //listen activity created, add it
                addActivity(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (null == activityList || activityList.isEmpty()) {
                    return;
                }
                if (activityList.contains(activity)) {
                    // listen activity destroyed, remove it
                    removeActivity(activity);
                }
            }
        });

    }
}
