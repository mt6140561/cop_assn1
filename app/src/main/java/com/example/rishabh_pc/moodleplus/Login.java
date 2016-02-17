package com.example.rishabh_pc.moodleplus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Rishab-pc on 17-Feb-16.
 */
public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void send(View v){
//        TextView te = (TextView)findViewById(R.id.usr);
//        String str = "hello";
//        te.setText((CharSequence)str);
        String username = ((EditText)findViewById(R.id.usrt)).getText().toString();
        String password = ((EditText)findViewById(R.id.passt)).getText().toString();
        RequestQueue mRequestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String url = "http://192.168.137.1:8000/default/login.json?userid=" + username + "&password=" + password;
        JsonObjectRequest jobjreq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
               TextView te = (TextView)findViewById(R.id.usrt);
                te.setText((CharSequence)response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });
        mRequestQueue.add(jobjreq);


    }
}
