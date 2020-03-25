package hu.bme.aut.vizivandor.ui.tura;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TuraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TuraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}