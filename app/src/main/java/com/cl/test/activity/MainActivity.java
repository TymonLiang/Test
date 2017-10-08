package com.cl.test.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cl.test.MyApp;
import com.cl.test.R;
import com.cl.test.constants.Constants;
import com.cl.test.model.Site;
import com.cl.test.net.BaseSubscriber;
import com.cl.test.net.IRxSiteRepository;
import com.cl.test.net.impl.RxSiteRepositoryImpl;
import com.cl.test.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by c.l on 16/08/2017.
 *  activity for task2
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.spinner_transport)
    Spinner spinnerTrans;

    @BindView(R.id.viewstub_text)
    ViewStub viewStubText;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private List<Site> siteList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private TextView textView;

    private Subscription rxSubscription;
    private IRxSiteRepository mRxSiteRepository;

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private double lat = 0.0;
    private double lng = 0.0;

    boolean isBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        removeSubscriber();
    }
    /**
     * init view in the activity
     */
    public void initView(){
        progressBar.setVisibility(View.VISIBLE);
        mRxSiteRepository = new RxSiteRepositoryImpl();
        getSites();

    }

    @OnItemSelected(R.id.spinner_transport)
    public void spinnerItemSelector(int position){
        LogUtil.d(Constants.TAG, "position: " + position);

        if(siteList.isEmpty()) return;

        lat = siteList.get(position).getLocation().getLatitude();
        lng = siteList.get(position).getLocation().getLongitude();

        if(siteList.get(position).getFromcentral().getTrain()== null){
            textView.setText(getResources().getString(R.string.test2_car,
                    siteList.get(position).getFromcentral().getCar()) );
            return;
        }

        textView.setText("Car - " + siteList.get(position).getFromcentral().getCar() +"\n"
                + "Train - " + siteList.get(position).getFromcentral().getTrain());
    }

    @OnClick(R.id.btn_navigate)
    public void onNavClick(){
        LogUtil.d(Constants.TAG, "nav clicked");

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);

        startActivity(MapActivity.class, bundle);

    }

    @OnClick(R.id.btn_next)
    public void onNextClick(){
        LogUtil.d(Constants.TAG, "next clicked");
        startActivity(TestOneActivity.class);
    }

    /**
     * get spinner info from network
     */
    private void getSites() {
        rxSubscription = mRxSiteRepository.getSiteData(1001,
                new BaseSubscriber<List<Site>>(){

                    @Override
                    public void onCompleted(){
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(List<Site> sites){
                        siteList.clear();
                        siteList.addAll(sites);
                        adapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        for(int i = 0; i < sites.size(); i++){
                            adapter.add(sites.get(i).getName());
                        }

                        spinnerTrans.setAdapter(adapter);
//                    spinnerTrans.setDropDownVerticalOffset(40);
//
                        viewStubText.inflate();
                        textView = (TextView) findViewById(R.id.tv_content);

                    }

                    @Override
                    public void onError(Throwable e){
                        super.onError(e);
                    }
                });

        addSubscriber(rxSubscription);
    }

    public void addSubscriber(Subscription subscription){
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void removeSubscriber() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i("ConfigureChanged", newConfig.toString());
    }

    @Override
    public void onBackPressed() {
        showToast("Press back again to exit");
        if (isBackPressed) {
            finish();
            MyApp.appExit();
        }
        new Thread() {
            public void run() {
                isBackPressed = true;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isBackPressed = false;
            }
        }.start();
    }

}
