package com.example.rishabh_pc.moodleplus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class Courses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        Log.d("bundle", bundle.size() + "");
        ArrayList<String[]> courses = new ArrayList<>();
        int i = 0;
        String tag = "c" + i;
        while (bundle.getStringArray(tag)!=null) {
            courses.add(bundle.getStringArray(tag));
            Log.d("each", bundle.getStringArray(tag).length + "");
            i = i+1;
            tag = "c" + i;

        }
        Log.d("experi", courses.size() + "");
    }

}
