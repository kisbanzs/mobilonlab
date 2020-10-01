package hu.bme.aut.vizivandor.ui.terkep;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TerkepViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TerkepViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}