package com.educationcube.cube.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.educationcube.cube.R;

public class MarketActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BottomNavigationView bottom_market;
    RelativeLayout relativesam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        relativesam=findViewById(R.id.relativesam);

        bottom_market = findViewById(R.id.bottom_market);
        bottom_market.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        bottom_market.setSelectedItemId(R.id.navigation_btm_social);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


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


                    return true;
                case R.id.navigation_btm_social:
                    startActivity(new Intent(MarketActivity.this, SocialMedia_Activity.class));
                    finish();
                    return true;
                case R.id.navigation_btm_link:
                    startActivity(new Intent(MarketActivity.this, RssCategory.class));
                    finish();
                    return true;
                case R.id.navigation_btn_chat:
                    startActivity(new Intent(MarketActivity.this, Chat_Activity.class));
                    finish();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        bottom_market.setSelectedItemId(R.id.navigation_btn_market);
    }

    public void onrelative(View view) {
        int currentitem=mViewPager.getCurrentItem();
        Log.e("test","click out"+currentitem);
        if(currentitem>=3){

        }else{
            mViewPager.setCurrentItem(currentitem+1);
            Log.e("test","click inside:"+currentitem);
        }
    }

    public void onPrevclick(View view) {
        int currentitem=mViewPager.getCurrentItem();
        if(currentitem==0){

        }else{
            mViewPager.setCurrentItem(currentitem-1);
        }
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        int tabvalue;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_marketupdate, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            ImageView image_market = rootView.findViewById(R.id.image_cardMarket);
            TextView text_marketfrag=rootView.findViewById(R.id.text_cardmarket);



             tabvalue = getArguments().getInt(ARG_SECTION_NUMBER);

//            textView.setText(getString(R.string.section_format, tabvalue));
            if (tabvalue == 1) {
                image_market.setImageResource(R.drawable.marketpage_one);

                text_marketfrag.setText(R.string.marketone);
            }
            if (tabvalue == 2) {
                image_market.setImageResource(R.drawable.marketpage_two);
                text_marketfrag.setText(R.string.markettwo);
            }
            if (tabvalue == 3) {
                image_market.setImageResource(R.drawable.marketpage_three);
                text_marketfrag.setText(R.string.marketthree);
            }
            if (tabvalue == 4) {
                image_market.setImageResource(R.drawable.marketpage_four);
                text_marketfrag.setText(R.string.marketfour);
            }
//            if(tabvalue==5){
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.education_to_emplayment));
//                relativeLayout.setBackgroundColor(getResources().getColor(R.color.));
//            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
RelativeLayout relativesam;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
