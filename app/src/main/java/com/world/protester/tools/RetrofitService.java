package com.world.protester.tools;

import com.world.protester.ProtesterApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {



    private static Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(ProtesterApplication.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
