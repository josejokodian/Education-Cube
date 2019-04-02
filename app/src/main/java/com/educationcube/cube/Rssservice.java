package com.educationcube.cube;

import akhil_patoliya.rsslibrary.RssFeed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Rssservice {
    @GET
    Call<RssFeed> getRss(@Url String url);
}
