package com.educationcube.cube.activities;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.educationcube.cube.R;

public class RssCategory extends AppCompatActivity {

    private String urlCanadaVis = "https://www.canadavisa.com/news/latest.html?format=feed&type=rss&_ga=2.72591891.1289710476.1551732142-1377483216.1551732142";
    private String urlScholarship = "https://www.scholarships-bourses.gc.ca/scholarships-bourses/rss/news-nouvelles_eng.xml";
    private String urlIelts="https://www.ieltspodcast.com/feed/";
    private String urlNews="https://thepienews.com/feed/";

    BottomNavigationView bottomnav_category;
    CardView card_visa;
    CardView card_scholarship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_category);

        intializeFuncCate();

    }

    private void intializeFuncCate() {
        card_visa = findViewById(R.id.card_visa);
        card_scholarship = findViewById(R.id.card_scholarship);
        bottomnav_category = findViewById(R.id.bottomnav_category);
        bottomnav_category.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomnav_category.setSelectedItemId(R.id.navigation_btm_link);

        card_visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(urlCanadaVis);
            }
        });
        card_scholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(urlScholarship);
            }
        });
    }

    private void intentCall(String url) {
        Intent rssIntent = new Intent(RssCategory.this, RssParserActivity.class);
        rssIntent.putExtra("url", url);
        startActivity(rssIntent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_btm_home:

                    finish();

                    return true;
                case R.id.navigation_btn_market:
                    startActivity(new Intent(RssCategory.this, MarketActivity.class));
                    finish();
                    return true;
                case R.id.navigation_btm_social:
                    startActivity(new Intent(RssCategory.this, SocialMedia_Activity.class));
                    finish();
                    return true;
                case R.id.navigation_btm_link:
                    return true;
                case R.id.navigation_btn_chat:
                    startActivity(new Intent(RssCategory.this, Chat_Activity.class));
                    finish();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        bottomnav_category.setSelectedItemId(R.id.navigation_btm_link);
    }

    public void OncardIeltsclick(View view) {
        intentCall(urlIelts);
    }

    public void onCardNewsClick(View view) {
        intentCall(urlNews);
    }
}
