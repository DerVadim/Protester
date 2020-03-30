package com.world.protester.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.world.protester.tools.NewsRepository;
import com.world.protester.wraps.EventWrap;

import java.util.List;

public class EventsViewModel extends ViewModel {

    private MutableLiveData<List<EventWrap>> mMutableLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mIsLoading=new MutableLiveData<>(false);
    public LiveData<Boolean> getIsLoading(){
        return this.mIsLoading;
    }

    private NewsRepository mNewsRepository;

    public void getEvents(String city){
        if (this.mNewsRepository == null)
            this.mNewsRepository = NewsRepository.getInstance();

        this.mNewsRepository.getNews(city,this.mMutableLiveData, this.mIsLoading);
    }


    public LiveData<List<EventWrap>> getNewsRepository() {
        return this.mMutableLiveData;
    }
}