package com.educationcube.cube.activities;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.educationcube.cube.R;
import com.educationcube.cube.RssFragment;


public class RssParserActivity extends AppCompatActivity {


    RecyclerView recyclerView_rss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssparser);

        String url=getIntent().getExtras().getString("url");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rss_relativelayout, RssFragment.newInstance(url))
                .commit();

//        alertDilougeFun();
    }

}
