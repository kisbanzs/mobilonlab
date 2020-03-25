package hu.bme.aut.vizivandor.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Legyél\n" +
                "te is vízivándor!\n" +
                "Fedezd fel a természetet olyan oldaláról, ami átlagon felüli! Csatlakozz azokhoz, akik már megtapasztalták, a különleges vízi világ nyújtotta kalandokat.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}