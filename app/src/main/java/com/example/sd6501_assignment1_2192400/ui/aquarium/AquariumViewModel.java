package com.example.sd6501_assignment1_2192400.ui.aquarium;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AquariumViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AquariumViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the aquarium fragment, aquariums will be selected and updated from here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}