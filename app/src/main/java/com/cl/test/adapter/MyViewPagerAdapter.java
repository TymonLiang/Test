package com.cl.test.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cl.test.R;
import com.cl.test.util.ImageLoader;
import com.cl.test.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c.l on 18/08/2017.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private List<Integer> images = new ArrayList<>();
    private Context context;

    public MyViewPagerAdapter(Context context, List<Integer> images){
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        if(images.size() == 0 || images == null){
            return 1;
        }
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtil.i("PagerAdapter" , "method instantiateItem be calling" );
        View v = LayoutInflater.from(context).inflate(R.layout.item_scroll_images , null);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);

        if(images.isEmpty()){
            img.setImageResource(R.drawable.test_bg);
        }else{
            ImageLoader.load(context.getApplicationContext(),
                    images.get(position),
                    R.drawable.test_bg,
                    img);
        }

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
