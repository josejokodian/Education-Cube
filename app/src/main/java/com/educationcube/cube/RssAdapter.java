package com.educationcube.cube;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


//import com.squareup.picasso.Picasso;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import akhil_patoliya.rsslibrary.RssItem;

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssViewHolder> {

    private final List<RssItem> mItems = new ArrayList<>();
    private OnItemClickListener mListener;
    private final Context mContext;
    WebView articleView;
    ProgressBar progressBar;
    RelativeLayout relative_webview;


    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    RssAdapter(Context context, WebView webview_rss, ProgressBar progress_rss, RelativeLayout relative_webview) {
        mContext = context;
        this.articleView = webview_rss;
        this.progressBar = progress_rss;
        this.relative_webview = relative_webview;
    }


    void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    void setItems(List<RssItem> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @NonNull
    @Override
    public RssViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rssrow, viewGroup, false);
        return new RssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssViewHolder rssViewHolder, int position) {

        mRequestQueue = Volley.newRequestQueue(mContext);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        if (mItems.size() <= position) {
            return;
        }
        final RssItem item = mItems.get(position);

        rssViewHolder.textTitle.setText(item.getTitle());

        if (item.getImage() != null) {
            Log.e("rssadapter", item.getImage().toString());

            String imagelink = item.getImage();
            String imagelink_char = imagelink.substring(0, Math.min(imagelink.length(), 1));
            String lastchar = imagelink.charAt(imagelink.length() - 1) + "";
            Log.e("rssadapter", imagelink_char);
            if (imagelink_char.equalsIgnoreCase("h")) {

                Log.e("rssadapter", lastchar + "");
                if (lastchar.equalsIgnoreCase("g")) {
                    rssViewHolder.imageView_rss.setImageUrl(item.getImage(), mImageLoader);
                }else{
                    rssViewHolder.relativerssimage.setVisibility(View.GONE);
                }
            } else {
                rssViewHolder.relativerssimage.setVisibility(View.GONE);
            }

        } else {
            rssViewHolder.relativerssimage.setVisibility(View.GONE);
        }
        rssViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relative_webview.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                articleView.setHorizontalScrollBarEnabled(true);
                articleView.setVerticalScrollBarEnabled(true);
                articleView.getSettings().setJavaScriptEnabled(true);
                articleView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        progressBar.setVisibility(View.GONE);
                    }
                });
                articleView.loadUrl(item.getLink());


            }
        });
        rssViewHolder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    interface OnItemClickListener {
        void onItemSelected(RssItem rssItem);
    }


    static class RssViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDate;
        NetworkImageView imageView_rss;
        RelativeLayout relativerssimage;

        public RssViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textDate = itemView.findViewById(R.id.text_date);
            imageView_rss = itemView.findViewById(R.id.image_rss);
            relativerssimage = itemView.findViewById(R.id.relativerssimage);

        }
    }
}

//                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
//                alertDialog.setTitle(item.getTitle());
//                alertDialog.setView(articleView);
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();