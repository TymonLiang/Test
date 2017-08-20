package com.cl.test.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cl.test.constants.Constants;
import com.cl.test.util.LogUtil;


/**
 * Created by c.l on 16/08/2017.
 * base activity
 */

public class BaseActivity extends AppCompatActivity {
    private Toast toast = null;

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(Constants.TAG, "Base onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(Constants.TAG, "Base onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(Constants.TAG, "Base onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(Constants.TAG, "Base onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(Constants.TAG, "Base onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(Constants.TAG, "Base onDestroy()");
    }

    /**
     * @param clz forward to next activity without params
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this,clz));
    }

    /**
     * forward to next activity with params
     * @param clz activity class
     * @param bundle data
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * @param tag toast info
     */
    protected void showToast(String tag){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), tag, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * @param i toast resource id
     */
    protected void showToast(int i){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), getResources().getString(i), Toast.LENGTH_SHORT);
        toast.show();
    }

}
