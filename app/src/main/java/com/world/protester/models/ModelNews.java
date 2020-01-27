package com.world.protester.models;

import android.util.Log;
import android.widget.Toast;

import com.world.protester.tools.GetDataService;
import com.world.protester.tools.RetrofitClientInstance;
import com.world.protester.wraps.NewsWrap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelNews {

    public void updateNews(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<NewsWrap>> call = service.getAllNews("Москва");
        call.enqueue(new Callback<List<NewsWrap>>() {
            @Override
            public void onResponse(Call<List<NewsWrap>> call, Response<List<NewsWrap>> response) {
                Log.d("Bla","onResponse");
            }

            @Override
            public void onFailure(Call<List<NewsWrap>> call, Throwable t) {
                Log.d("Bla","fail");
            }
        });
    }


}
