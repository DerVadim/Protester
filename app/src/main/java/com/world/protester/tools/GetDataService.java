package com.world.protester.tools;

import com.world.protester.wraps.NewsWrap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23
public interface GetDataService {

    @GET("/news")
    Call<List<NewsWrap>> getAllNews(String city);
}
