package com.example.rishabh_pc.moodleplus;

import android.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rishab-pc on 24-Feb-16.
 */
public class threadPartOnClick implements View.OnClickListener {

    public MyAct myAct;
    public String[] params;

    public threadPartOnClick(String[] params , MyAct myAct) {
        this.params = params;

        this.myAct = myAct;
    }

    public void onClick(View v) {
        myAct.threadJSON(params);
    }

}
