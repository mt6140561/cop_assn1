package com.example.rishabh_pc.moodleplus;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rishab-pc on 23-Feb-16.
 */
public class threadOnClick implements View.OnClickListener {
    public coursedata allc;
    public FragmentManager fm;
    public MyAct myAct;

    public threadOnClick(coursedata allc, FragmentManager fm, MyAct myAct) {
        this.allc = allc;
        this.fm = fm;
        this.myAct = myAct;
    }

    public void onClick(View v) {
        Requeue requeue = myAct.requeue;
        String coursecode = allc.basics[0];
        Log.d("course in thread", coursecode);
        String url = "http://192.168.137.1:8000/courses/course.json/"+ coursecode +"/threads";
        ParaJson jobjre = new ParaJson(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    ArrayList<String[]> all_threads = new ArrayList<>();
                    JSONArray courset = response.getJSONArray("course_threads");
                    for (int i=0; i<courset.length(); i++) {
                        String[] putin = new String[6];
                        putin[0] = (courset.getJSONObject(i).getString("user_id"));
                        putin[1] = courset.getJSONObject(i).getString("description");
                        putin[2] = courset.getJSONObject(i).getString("title");
                        putin[3] = courset.getJSONObject(i).getString("created_at");
                        putin[4] = courset.getJSONObject(i).getString("updated_at");
                        putin[5] = courset.getJSONObject(i).getString("id");
                        all_threads.add(putin);



                }
                    Log.d("thread", "aa gaya response");
                    fm.beginTransaction()
                            .replace(R.id.blanklayout, new threads().newInstance(all_threads))
                            .commit();

                } catch (Exception e) {}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "yeh hua");
            }
        });
        requeue.add(jobjre);

    }
}
