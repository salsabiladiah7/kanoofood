package com.mafaa.kanoofood.admin_ui.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PaymentViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public PaymentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is payment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}