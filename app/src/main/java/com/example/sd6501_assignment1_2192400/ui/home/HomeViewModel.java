package com.example.sd6501_assignment1_2192400.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the home fragment, important info and reminders will be displayed here.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}