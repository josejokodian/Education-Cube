package com.educationcube.cube.activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.educationcube.cube.R;

public class Home_activity extends AppCompatActivity {

    private TextView mTextMessage;

    BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_btm_home:
                    return true;
                case R.id.navigation_btn_market:
                    startActivity(new Intent(Home_activity.this, MarketActivity.class));

                    return true;
                case R.id.navigation_btm_social:
                    startActivity(new Intent(Home_activity.this, SocialMedia_Activity.class));

                    return true;
                case R.id.navigation_btm_link:
                    startActivity(new Intent(Home_activity.this, RssCategory.class));

                    return true;
                case R.id.navigation_btn_chat:
                    startActivity(new Intent(Home_activity.this, Chat_Activity.class));

                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_btm_home);
        getBrwosetcall();
    }

    private void getBrwosetcall() {
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getBrwosetcall();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_btm_home);
    }

    public void onMapbuttonclicked(View view) {
//        MyCustomDialog();
        startActivity(new Intent(Home_activity.this, MapsActivity.class));
    }

    public void MyCustomDialog() {

        CardView card_dialogmap=findViewById(R.id.card_dialogmap);
        card_dialogmap.setVisibility(View.VISIBLE);
        Spinner spinner_provinceMap=findViewById(R.id.spinner_mapprovince);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.province, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_provinceMap.setAdapter(adapter);


    }
}
