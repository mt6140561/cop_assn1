package com.example.rishabh_pc.moodleplus;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by Rishab-pc on 19-Feb-16.
 */
public class Requeue extends RequestQueue {

    public Requeue(Context context) {
        super(new DiskBasedCache(context.getCacheDir(), 1024 * 1024), new BasicNetwork(new HurlStack()));
    }

}
