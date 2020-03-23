package com.world.protester.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
    }

    public void setText(String text){
        this.mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}