package com.world.protester.tools;

import androidx.lifecycle.MutableLiveData;

import com.world.protester.wraps.NewsWrap;
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

    public MutableLiveData<List<NewsWrap>> getNews(String key){

        final MutableLiveData<List<NewsWrap>> newsData = new MutableLiveData<>();
        newsApi.getAllNews(key).enqueue(new Callback<List<NewsWrap>>() {
            @Override
            public void onResponse(Call<List<NewsWrap>> call, Response<List<NewsWrap>> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<NewsWrap>> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }




}
