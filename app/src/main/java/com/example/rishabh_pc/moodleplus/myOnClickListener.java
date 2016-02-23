package com.example.rishabh_pc.moodleplus;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

/**
 * Created by Rishab-pc on 23-Feb-16.
 */
public class myOnClickListener implements View.OnClickListener {
    public String[] params;
    public FragmentManager fm;
    private View v;

    public myOnClickListener(String[] params, FragmentManager fm){

        this.params = params;
        this.fm = fm;



    }

    public void onClick(View v) {
        this.v = v;
        Log.d("click", params[0]);

        fm.beginTransaction()
                .replace(R.id.blanklayout, new coursedata().newInstance(params))
                .commit();
    }
}
