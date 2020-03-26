package com.world.protester.tools;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.world.protester.ProtesterApplication;
import com.world.protester.wraps.EventWrap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private GetDataService newsApi;


    public NewsRepository(){
        newsApi = RetrofitService.cteateService(GetDataService.class);
    }

    public MutableLiveData<List<EventWrap>> getNews(String key,MutableLiveData<Boolean> isLoading){

        final MutableLiveData<List<EventWrap>> newsData = new MutableLiveData<>();
        isLoading.setValue(true);
        Log.d("LOADING","getNews"+String.valueOf(isLoading.getValue()));
        newsApi.getAllNews(key).enqueue(new Callback<List<EventWrap>>() {
            @Override
            public void onResponse(Call<List<EventWrap>> call, Response<List<EventWrap>> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<List<EventWrap>> call, Throwable t) {
                Log.d(ProtesterApplication.PROTESTER,"Error getting news!");
                newsData.setValue(null);
                isLoading.setValue(false);
            }
        });
        return newsData;
    }




}
