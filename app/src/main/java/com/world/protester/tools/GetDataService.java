package com.world.protester.tools;

import com.world.protester.wraps.NewsWrap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23
public interface GetDataService {

    @GET("data/{city}")
    Call<List<NewsWrap>> getAllNews( @Path("city") String city);
}
