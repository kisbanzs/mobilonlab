package hu.bme.aut.vizivandor.ui.tura;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hu.bme.aut.vizivandor.R;

public class TuraFragment extends Fragment {

    private TuraViewModel turaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        turaViewModel =
                ViewModelProviders.of(this).get(TuraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tura, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        turaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}