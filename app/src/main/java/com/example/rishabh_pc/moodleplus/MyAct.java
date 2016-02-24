package com.example.rishabh_pc.moodleplus;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MyAct extends Login
        implements NavigationView.OnNavigationItemSelectedListener {
 static Requeue requeue;
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "session_id_moodleplus";

    private static MyAct _instance;
    private SharedPreferences _preferences;
    private overview over;
    private ArrayList<String[]> courses;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        courses = new ArrayList<>();
       _instance = this;
       _preferences = PreferenceManager.getDefaultSharedPreferences(this);
       this.requeue = new Requeue(this);
       requeue.start();


        Bundle bundle = getIntent().getExtras();
        String abc =bundle.size()+"";
        Log.d("length", abc );
        String Sescook = bundle.getString("sess");
       Log.d("cookie", Sescook);
       SharedPreferences.Editor prefEditor = _preferences.edit();
       prefEditor.putString(SESSION_COOKIE, Sescook);
       prefEditor.commit();

        String fn = bundle.getString("first_name");
        String ln = bundle.getString("last_name");
        String welcome = "Welcome " + fn + " " + ln;
       Log.d("yeh sahi hai", welcome);
       over = (new overview()).newInstance(fn, ln);


       getFragmentManager().beginTransaction()
               .replace(R.id.blanklayout, over)
               .commit();
//        try {
//        Log.d("Size", (response.getJSONObject("user")).length() + "");} catch (Exception e) {Log.d("Exception", e.getMessage().toString());}



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       Menu menu = navigationView.getMenu();
       SubMenu subMenu = menu.addSubMenu("Your courses");
       int i = 0;
       String tag = "c" + i;
       Log.d("array", bundle.getStringArray(tag).toString());

       while (bundle.getStringArray(tag)!=null) {
           String[] ret = getIntent().getStringArrayExtra(tag);
           courses.add(ret);
           subMenu.add(0, i, (i*100), ret[0]+": "+ ret[1]);
            i = i+1;
           Log.d("machaya phir", tag);
           tag = "c" + i;


       }
    }

    public static MyAct get() {
        return _instance;
    }





//    public void onClick(SubMenu sub) {
//        String name = sub.
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.nav_camera) {
            final allcourses allc = new allcourses();
               String url = "http://192.168.137.1:8000/courses/list.json";
               Log.d("frag", "yeh bhi hua");
               ParaJson jobjre = new ParaJson(url, new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {

                       String[][] star = convert(response);
                       Fragment ret = allc.newInstance(star);
                       fm.beginTransaction()
                               .replace(R.id.blanklayout, ret)
                               .commit();

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Log.d("error", "yeh hua");
                   }
               }, this);

               requeue.add(jobjre);


        } else if (id == R.id.grades) {

            final grades allg = new grades();
            String url = "http://192.168.137.1:8000/default/grades.json";
            Log.d("frag", "yeh bhi hua");
            ParaJson jobjre = new ParaJson(url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                  String[][] star=convertforgrades(response);
//         Log.d("abc",star.length+"");
                    Fragment ret = allg.newInstance(star);



                    fm.beginTransaction()
                            .replace(R.id.blanklayout, ret)
                            .commit();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", "yeh hua");
                }
            }, this);
            requeue.add(jobjre);
        } else if (id == R.id.overview) {
            fm.beginTransaction()
                    .replace(R.id.blanklayout, over)
                    .commit();
        } else if (id == R.id.notif) {
            String url = "http://192.168.137.1:8000/default/notifications.json";

            ParaJson jobjre = new ParaJson(url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    String[][] star = convert(response);
//                    Fragment ret = allc.newInstance(star);
//                    fm.beginTransaction()
//                            .replace(R.id.blanklayout, ret)
//                            .commit();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", "yeh hua");
                }
            }, this);

            requeue.add(jobjre);

        } else {
            int i=0;
            while (i<courses.size()) {
               if (id == i ){
                   fm.beginTransaction()
                           .replace(R.id.blanklayout, new coursedata().newInstance(courses.get(i)))
                           .commit();
                   Log.d("tu sahi hai", i+"");
               }
               i = i+1;

           }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private String[][] convert(JSONObject json) {
        try {
            JSONArray arr = json.getJSONArray("courses");
            String[][] ret = new String[arr.length()][4];


            for (int i = 0; i < arr.length(); i++) {
                ret[i][0] = arr.getJSONObject(i).getString("code");
                ret[i][1] = arr.getJSONObject(i).getString("name");
                ret[i][2] = arr.getJSONObject(i).getString("description");
                ret[i][3] = arr.getJSONObject(i).getString("id");
            }
            return ret;
        } catch (Exception e) {
            Log.d("Excep", e.getMessage().toString());
            return null;
        }
    }


    private String[][] convertforgrades(JSONObject json) {
        try {
            JSONArray arr = json.getJSONArray("grades");
            JSONArray arr1 = json.getJSONArray("courses");

            String[][] ret1 = new String[arr.length()+1][6];
            ret1[0][0] = "Weightage";
            ret1[0][1] = "User_id";
            ret1[0][2] = "Name"+"       ";
            ret1[0][3] ="Maximum Marks";
            ret1[0][5] = "Score";
            ret1[0][4]= "Course Code";


            for (int i = 1; i < arr.length()+1; i++) {
                ret1[i][0] = arr.getJSONObject(i-1).getString("weightage")+"      ";
                ret1[i][1] = arr.getJSONObject(i-1).getString("user_id")+"      ";
                ret1[i][2] = arr.getJSONObject(i-1).getString("name");
                ret1[i][3] = arr.getJSONObject(i-1).getString("out_of");
                ret1[i][5] = arr.getJSONObject(i-1).getString("score");
                ret1[i][4]= arr1.getJSONObject(i-1).getString("code");
                Log.d("abgghbbb",ret1[i].length+"");
            }

            Log.d("abbbb",ret1.length + "");



            return ret1;
        } catch (Exception e) {
            Log.d("Excepaasasasa", e.getMessage().toString());
            return null;

        }


    }


    public void threadsJSON(coursedata dat, Button butt) {
        threadOnClick toc = new threadOnClick(dat, getFragmentManager(), this);
        Log.d("nope", "yep");

        butt.setOnClickListener(toc);
    }

    public void threadJSON(String[] params) {
//        final String[] pass = params;
        final FragmentManager fm = getFragmentManager();
        final Fragment thread = new thread();
        final Bundle args = new Bundle();



        String url = "http://192.168.137.1:8000/users/user.json/"+pass[0];
        ParaJson jobjre = new ParaJson(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                        JSONObject json = response.getJSONObject("user");

                    String fullname = json.getString("first_name")+" "+json.getString("last_name");
                    Log.d("bhai", fullname);
                    args.putString("sender", fullname);

                    thread.setArguments(args);
                    fm.beginTransaction()
                            .replace(R.id.blanklayout, thread)
                            .commit();

//                    fm.beginTransaction()
//                            .replace(R.id.blanklayout, new thread().newInstance(fullname, pass))
//                            .commit();

                } catch (Exception e) {}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "yeh hua");
            }
        }, this);
        requeue.add(jobjre);
        String url2 = "http://192.168.137.1:8000/users/user.json/"+pass[0];
        ParaJson jobjre2 = new ParaJson(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject json = response.getJSONObject("user");

                    String fullname = json.getString("first_name")+" "+json.getString("last_name");
                    Log.d("bhai", fullname);
                    args.putString("sender", fullname);
                    args.putStringArray("param2", pass);
                    thread.setArguments(args);
                    fm.beginTransaction()
                            .replace(R.id.blanklayout, thread)
                            .commit();

//                    fm.beginTransaction()
//                            .replace(R.id.blanklayout, new thread().newInstance(fullname, pass))
//                            .commit();

                } catch (Exception e) {}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "yeh hua");
            }
        }, this);
        requeue.add(jobjre);

        Log.d("bhai", "out");



    }

    public void newthread(String coursecode) {
        final MyAct main = this;
        String title = ((EditText)findViewById(R.id.box1)).getText().toString();
        String descr = ((EditText)findViewById(R.id.box2)).getText().toString();
        String[] titl = title.split("\\s+");
        String[] desc = descr.split("\\s+");
        String actitl = "";
        String actdes = "";
        for(int i=0; i<titl.length; i++){
            actitl = actitl +"%20" +titl[i];
        }
        for(int j=0; j<desc.length; j++){
            actdes = actdes +"%20" +desc[j];
        }
        Log.d("thread", actitl + " " + actdes);
        String url = "http://192.168.137.1:8000/threads/new.json?title="+actitl+"&description="+actdes+"&course_code="+coursecode;
        ParaJson jobjre = new ParaJson(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    String bool = response.getString("success");
//                    if(bool.equals("true")) {
//                        threadOnClick toc = new threadOnClick(cour, getFragmentManager(), main);
//                        toc.onClick((TextView)findViewById(R.id.sender));
//                    }
                    Log.d("new", "new thread added");
                } catch (Exception e) {}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "yeh hua");
            }
        }, this);
        requeue.add(jobjre);

    }
}





