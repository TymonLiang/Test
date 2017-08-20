package com.cl.test.activity;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cl.test.R;
import com.cl.test.adapter.MyViewPagerAdapter;
import com.cl.test.util.LogUtil;
import com.cl.test.view.IndicatorView;
import com.cl.test.view.MyViewPager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by c.l on 16/08/2017.
 * activity for task1
 */
public class TestOneActivity extends BaseActivity implements MyViewPager.OnPageChangeListener{
    @BindView(R.id.ll_test_one)
    LinearLayout linearLayoutAll;

    //header layout
    @BindView(R.id.hsv_top)
    HorizontalScrollView scrollView;

    @BindView(R.id.ll_header)
    LinearLayout linearLayoutTop;

    //viewpager layout
    @BindView(R.id.vp_top)
    MyViewPager viewPagerTop;

    @BindView(R.id.iv_top)
    IndicatorView indicatorViewTop;

    //upper bottom layout
    @BindView(R.id.tv_display)
    TextView textViewDisplay;

    private static int currentItem = 0;
    private int oldPosition = 0;
    private int topNumber = 0;
    //schedule tasks for viewpager
    private ScheduledExecutorService scheduledExecutorService;
    private final Integer[] images = {R.drawable.test_bg1, R.drawable.test_bg2, R.drawable.test_bg3};

    static class MyHandler extends Handler {
        //add weakReference to avoid memory leak
        WeakReference<MyViewPager> mViewPagerWeakReference;

        MyHandler(MyViewPager viewPager) {
            mViewPagerWeakReference= new WeakReference<>(viewPager);
        }

        @Override
        public void handleMessage(Message msg) {
            MyViewPager viewPager = mViewPagerWeakReference.get();

            if (viewPager != null) {
                viewPager.setCurrentItem(currentItem);
            }
        }
    }
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one);
        ButterKnife.bind(this);

        initView();
    }

    private void initView(){
        topNumber = images.length;
        indicatorViewTop.setIndicatorSize(3, R.drawable.icon_dot_stroke, R.drawable.icon_dot_full);
        List<Integer> lists = new ArrayList<>(Arrays.asList(images));

        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(this, lists);
        viewPagerTop.setAdapter(viewPagerAdapter);
        handler  = new MyHandler(viewPagerTop);
        viewPagerTop.setOffscreenPageLimit(3);
        viewPagerTop.addOnPageChangeListener(this);
        viewPagerTop.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                indicatorViewTop.checkIndex(position);
            }
        });
        viewPagerTop.setOnSingleTouchListener(new MyViewPager.OnSingleTouchListener() {
            @Override
            public void onSingleTouch(boolean isTouching) {
                LogUtil.i("isTouching", isTouching+"");
                if(isTouching){
                    showToast("Image: "+currentItem);
                }
            }
        });

    }

    @OnClick({R.id.tv_item1, R.id.tv_item2, R.id.tv_item3, R.id.tv_item4, R.id.tv_item5})
    public void onHeaderClick(View v){
        switch (v.getId()){
            case R.id.tv_item1:
                textViewDisplay.setText(getResources().getString(R.string.test_string, "Item 1"));
                break;
            case R.id.tv_item2:
                textViewDisplay.setText(getResources().getString(R.string.test_string, "Item 2"));
                break;
            case R.id.tv_item3:
                textViewDisplay.setText(getResources().getString(R.string.test_string, "Item 3"));
                break;
            case R.id.tv_item4:
                textViewDisplay.setText(getResources().getString(R.string.test_string, "Item 4"));
                break;
            case R.id.tv_item5:
                textViewDisplay.setText(getResources().getString(R.string.test_string, "Item 5"));
                break;
        }
    }

    @OnClick(R.id.btn_red)
    public void onRedClick(){
        linearLayoutAll.setBackgroundResource(android.R.color.holo_red_light);
    }

    @OnClick(R.id.btn_blue)
    public void onBlueClick(){
        linearLayoutAll.setBackgroundResource(android.R.color.holo_blue_light);
    }

    @OnClick(R.id.btn_green)
    public void onGreenClick(){
        linearLayoutAll.setBackgroundResource(android.R.color.holo_green_light);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("ScheduleService", "------->start");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }

    @Override
    public void onStop(){
        LogUtil.i("ScheduleService", "------->finish");

        if (null != scheduledExecutorService) {
            scheduledExecutorService.shutdown();
        }
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        oldPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPagerTop) {
                currentItem = (currentItem + 1) % topNumber;
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i("ConfigureChanged", newConfig.toString());

        if(this.getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_LANDSCAPE){
            textViewDisplay.setLines(1);
        }else if (this.getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_PORTRAIT){
            textViewDisplay.setLines(4);
        }
    }
}
