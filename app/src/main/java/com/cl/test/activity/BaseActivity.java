package com.cl.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


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
}
