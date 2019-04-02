package com.educationcube.cube.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.educationcube.cube.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class SocialMedia_Activity extends AppCompatActivity {


    CardView cardView_social;
    private PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    String videopath = "https://www.education-cube.com/uploads/Biju_Shot_1_-_Export_1.mp4";
    BottomNavigationView navigation;


    CardView card_fb, card_twitter, card_youtube, card_linkedin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_);

        playerView = findViewById(R.id.exoplayer);

        navigation = findViewById(R.id.navigation_social);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_btm_social);

        card_fb = findViewById(R.id.card_fb);
        card_twitter = findViewById(R.id.card_twitter);
        card_youtube = findViewById(R.id.card_youtube);
        card_linkedin = findViewById(R.id.card_linkedin);

        card_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLink();
            }
        });
        card_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterLink();
            }
        });
        card_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeLink();
            }
        });
        card_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Comming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(simpleExoPlayer);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "Exoplayer"));

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videopath));
        simpleExoPlayer.prepare(mediaSource);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        simpleExoPlayer.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        simpleExoPlayer.setPlayWhenReady(true);

    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        simpleExoPlayer.release();
        simpleExoPlayer = null;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        navigation.setSelectedItemId(R.id.navigation_btm_social);
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_btm_home:

                    finish();
                    return true;
                case R.id.navigation_btn_market:
                    startActivity(new Intent(SocialMedia_Activity.this, MarketActivity.class));
                    finish();
                    return true;
                case R.id.navigation_btm_social:
                    return true;
                case R.id.navigation_btm_link:
                    startActivity(new Intent(SocialMedia_Activity.this, RssCategory.class));
                    finish();
                    return true;
                case R.id.navigation_btn_chat:
                    startActivity(new Intent(SocialMedia_Activity.this, Chat_Activity.class));
                    finish();
                    return true;
            }
            return false;
        }
    };

    private void facebookLink() {
        String fbId = "269209090507052";
        try {
            Intent intentfb = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + fbId));
            startActivity(intentfb);
        } catch (ActivityNotFoundException e) {
            Intent intentfb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + fbId));
            startActivity(intentfb);
        }
    }

    private void twitterLink() {
        String twitter_user_name = "education_cube_";
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitter_user_name)));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitter_user_name)));
        }
    }

    private void youtubeLink() {
        Intent intentyou = null;
        String youtubeurl = "https://www.youtube.com/channel/UCOv_ME5p0gF3Vtsd5YE7Okw/";
        try {
            intentyou = new Intent(Intent.ACTION_VIEW);
            intentyou.setPackage("com.google.android.youtube");
            intentyou.setData(Uri.parse(youtubeurl));
            startActivity(intentyou);
        } catch (ActivityNotFoundException e) {
            intentyou = new Intent(Intent.ACTION_VIEW);
            intentyou.setData(Uri.parse(youtubeurl));
            startActivity(intentyou);
        }
    }

}
