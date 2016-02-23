package com.example.rishabh_pc.moodleplus;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Rishab-pc on 17-Feb-16.
 */
public class Login extends AppCompatActivity {
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "session_id_moodleplus";

    private static Login _instance;
    private static Requeue requeue;

    private SharedPreferences _preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _instance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.requeue = new Requeue(this);
        requeue.start();

        setContentView(R.layout.login);


    }
        public static Login get() {
            return _instance;
        }


        public final void checkSessionCookie(Map<String, String> headers) {
            if (headers.containsKey(SET_COOKIE_KEY) && headers.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)) {
                String cookie = headers.get(SET_COOKIE_KEY);
                if (cookie.length() > 0) {
                    String[] splitCookie = cookie.split(";");
                    String[] splitSessionId = splitCookie[0].split("=");
                    cookie = splitSessionId[1];
                    SharedPreferences.Editor prefEditor = _preferences.edit();
                    prefEditor.putString(SESSION_COOKIE, cookie);
                    prefEditor.commit();
                }
            }
        }

        /**
         * Adds session cookie to headers if exists.
         * @param headers
         */
    public final void addSessionCookie(Map<String, String> headers) {
        String sessionId = _preferences.getString(SESSION_COOKIE, "");
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (headers.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE_KEY));
            }

            headers.put(COOKIE_KEY, builder.toString());

        }
    }


    public void send(View v){


        final Intent intent = new Intent(this, MyAct.class);
//        TextView te = (TextView)findViewById(R.id.usr);
//        String str = "hello";
//        te.setText((CharSequence)str);
        String username = ((EditText)findViewById(R.id.usr)).getText().toString();
        String password = ((EditText)findViewById(R.id.pass)).getText().toString();


        String url = "http://10.192.39.119:8000/default/login.json?userid=" + username + "&password=" + password;
        ParaJson jobjreq = new ParaJson(url, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
               try {

                   String bool = response.getString("success");

                   if (bool.equals("true"))
                   {
                       String url2 = "http://192.168.137.1:8000/courses/list.json";

                       ParaJson jobjre = new ParaJson(url2, new Response.Listener<JSONObject>() {
                           @Override
                           public void onResponse(JSONObject response) {
                               try {
                                   JSONArray arr = response.getJSONArray("courses");
                                   String tag;
                                   for (int i = 0; i < arr.length(); i++) {
                                       String[] ret = new String[4];
                                       ret[0] = arr.getJSONObject(i).getString("code");
                                       ret[1] = arr.getJSONObject(i).getString("name");
                                       ret[2] = arr.getJSONObject(i).getString("description");
                                       ret[3] = arr.getJSONObject(i).getString("id");
                                       tag = "c" + i;
                                       intent.putExtra(tag, ret);
                                   }
                                   JSONObject user = response.getJSONObject("user");
                                   intent.putExtra("last_name", user.getString("last_name"));
                                   intent.putExtra("first_name", user.getString("first_name"));
                                   intent.putExtra("type_", user.getString("type_"));
                                   startActivity(intent);
                               } catch (Exception e) {Log.d("course while login", e.getMessage());}

                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               Log.d("error", "yeh hua");
                           }
                       });


                       requeue.add(jobjre);


                   }
               } catch (Exception e) {Log.d("this", e.getMessage().toString());}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });

        requeue.add(jobjreq);


    }




}
