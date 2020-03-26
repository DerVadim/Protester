package com.world.protester.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.world.protester.tools.NewsRepository;
import com.world.protester.wraps.EventWrap;

import java.util.List;

public class EventsViewModel extends ViewModel {

    private MutableLiveData<List<EventWrap>> mMutableLiveData;

    private final MutableLiveData<Boolean> mIsLoading=new MutableLiveData<>();
    public LiveData<Boolean> getIsLoading(){
        return this.mIsLoading;
    }

    private NewsRepository mNewsRepository;

    public void getEvents(String city){
        if (this.mMutableLiveData != null){
            return;
        }
        this.mNewsRepository = NewsRepository.getInstance();
        this.mMutableLiveData = this.mNewsRepository.getNews(city, mIsLoading);
    }


    public LiveData<List<EventWrap>> getNewsRepository() {
        return this.mMutableLiveData;
    }
}