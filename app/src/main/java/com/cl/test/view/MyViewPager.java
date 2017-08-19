package com.cl.test.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cl.test.constants.Constants;
import com.cl.test.util.LogUtil;

/**
 * Created by c.l on 18/08/2017.
 */

public class MyViewPager extends ViewPager {

    OnSingleTouchListener onSingleTouchListener;
    private boolean mIsTouching = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mIsTouching = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mIsTouching = false;
                break;
        }

        onSingleTouch(mIsTouching);

        return super.onTouchEvent(ev);
    }

//    @Override
//    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//
//        LogUtil.i(Constants.TAG, "ViewPagerSize: "+ width +"x" +height);
//
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (width * 0.75), MeasureSpec.AT_MOST);
//
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }


    public void onSingleTouch(boolean isTouching) {
        if (onSingleTouchListener!= null) {
            onSingleTouchListener.onSingleTouch(isTouching);
        }
    }

    public interface OnSingleTouchListener {
        void onSingleTouch(boolean isTouching);
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }
}
