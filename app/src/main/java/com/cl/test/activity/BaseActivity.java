package com.cl.test.activity;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cl.test.util.LogUtil;


/**
 * Created by c.l on 16/08/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private Toast toast = null;

    public void showToast(int i){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), String.valueOf(i), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i("BaseActivity ConfigureChanged", newConfig.toString());
    }
}
