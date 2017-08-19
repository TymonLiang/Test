package com.cl.test.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by c.l on 19/08/2017.
 * Envelop image, providing a unified entrance
 */

public class ImageLoader {

    public static void load(Context context, int url, int ph, ImageView iv){
        Picasso.with(context).load(url).resize(800, 600).centerCrop().placeholder(ph).into(iv);
    }
}
