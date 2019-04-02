package com.educationcube.cube;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.List;

import akhil_patoliya.rsslibrary.RssConverterFactory;
import akhil_patoliya.rsslibrary.RssFeed;
import akhil_patoliya.rsslibrary.RssItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RssFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RssAdapter.OnItemClickListener {

    private static final String KEY_FEED = "FEED";

    private String mFeedUrl;
    private RssAdapter mAdapter;

    RecyclerView recyclerView_rss;
    SwipeRefreshLayout swipeRefresh;
    WebView webview_rss;
    ProgressBar progress_rss;
    Button btn_rss;
    RelativeLayout relative_webview;



    public static RssFragment newInstance(String feedUrl) {
        RssFragment rssFragment = new RssFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FEED, feedUrl);
        rssFragment.setArguments(bundle);

        return rssFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFeedUrl = getArguments().getString(KEY_FEED);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss, container, false);
        recyclerView_rss=view.findViewById(R.id.recyclerView_rss);
        swipeRefresh=view.findViewById(R.id.swipeRefresh);
        webview_rss=view.findViewById(R.id.webview_rss);
        progress_rss=view.findViewById(R.id.progress_rss);
        relative_webview=view.findViewById(R.id.relative_webview);
        btn_rss=view.findViewById(R.id.btn_rss_webview);
        btn_rss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_webview.setVisibility(View.GONE);
                progress_rss.setVisibility(View.GONE);
                webview_rss.clearHistory();
                webview_rss.clearCache(true);
                webview_rss.loadUrl("");
            }
        });

        mAdapter = new RssAdapter(getActivity(),webview_rss,progress_rss,relative_webview);
        mAdapter.setListener((RssAdapter.OnItemClickListener) this);
        recyclerView_rss.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_rss.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(this);


        fetchRss();
        return view;
    }

    private void fetchRss() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(RssConverterFactory.create())
                .build();

        showLoading();
        Rssservice service = retrofit.create(Rssservice.class);
        service.getRss(mFeedUrl)
                .enqueue(new Callback<RssFeed>() {
                    @Override
                    public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                        onRssItemsLoaded(response.body().getItems());
                        hideLoading();
                    }

                    @Override
                    public void onFailure(Call<RssFeed> call, Throwable t) {
                        Log.e("retrofit",t.getMessage());
                        Toast.makeText(getActivity(), "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show();
                        getActivity().finish();

                    }
                });

    }

    public void onRssItemsLoaded(List<RssItem> rssItems) {
        mAdapter.setItems(rssItems);
        mAdapter.notifyDataSetChanged();
        if (recyclerView_rss.getVisibility() != View.VISIBLE) {
            recyclerView_rss.setVisibility(View.VISIBLE);
        }
    }

    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        fetchRss();
    }


    @Override
    public void onItemSelected(RssItem rssItem) {
        Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
    }
}
