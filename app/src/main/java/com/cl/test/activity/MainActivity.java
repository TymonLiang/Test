package com.cl.test.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.cl.test.R;
import com.cl.test.constants.Constants;
import com.cl.test.model.Site;
import com.cl.test.net.NetManager;
import com.cl.test.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @BindView(R.id.spinner_transport)
    Spinner spinnerTrans;

    @BindView(R.id.viewstub_text)
    ViewStub viewStubText;

    private List<String> names = new ArrayList<>();
    private List<Site> sites = new ArrayList<>();
    //spinner adapter
    private ArrayAdapter<String> adapter;
    private TextView textView;
    private ProgressDialog progress;

    private double lat = 0.0;
    private double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    public void initView(){

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
//        List<String> title = new ArrayList<>();
//        title.add("Please select");
        getSite();

    }

    @OnItemSelected(R.id.spinner_transport)
    public void spinnerItemSelector(int position){
        LogUtil.d(Constants.TAG, "position: " + position);

        if(sites.isEmpty()) return;

        lat = sites.get(position).getLocation().getLatitude();
        lng = sites.get(position).getLocation().getLongitude();

        if(sites.get(position).getFromcentral().getTrain()== null){
            textView.setText(getResources().getString(R.string.test2_car,
                    sites.get(position).getFromcentral().getCar()) );
            return;
        }

        textView.setText("Car - " + sites.get(position).getFromcentral().getCar() +"\n"
                + "Train - " + sites.get(position).getFromcentral().getTrain());
    }

    @OnClick(R.id.btn_navigate)
    public void onNavClick(){
        LogUtil.d(Constants.TAG, "nav clicked");
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.btn_next)
    public void onNextClick(){
        LogUtil.d("Constants.TAG", "next clicked");
        startActivity(new Intent(MainActivity.this, TestOneActivity.class));
    }

    private void getSite(){
        Call<List<Site>> siteCall = NetManager.getInstance().getSiteService().getSite();

        siteCall.enqueue(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                LogUtil.d(Constants.TAG, response.toString());
                if (response.body() != null) {
                    sites.clear();
                    sites.addAll(response.body());
                    names.clear();
                    for(int i = 0; i < response.body().size(); i++){
                        names.add(response.body().get(i).getName());
                    }


                    adapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTrans.setAdapter(adapter);
                    spinnerTrans.setDropDownVerticalOffset(40);

                    viewStubText.inflate();
                    textView = (TextView) findViewById(R.id.tv_content);
                    progress.cancel();

                }
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                LogUtil.d(Constants.TAG, call.toString());
                LogUtil.d(Constants.TAG, t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i("ConfigureChanged", newConfig.toString());
    }

}
