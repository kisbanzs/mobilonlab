/*package hu.bme.aut.vizivandor.ui.terkep;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hu.bme.aut.vizivandor.R;

public class TerkepActivity extends AppCompatActivity {

    //private TerkepViewModel terkepViewModel;
    private OsmdroidActivity osmdroidActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.osmdroid_map);

    }

    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        terkepViewModel =
                ViewModelProviders.of(this).get(TerkepViewModel.class);
        View root1 = inflater.inflate(R.layout.fragment_terkep, container, false);
        final TextView textView = root1.findViewById(R.id.text_slideshow);
        terkepViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        osmdroidActivity = ViewModelProviders.of(this).get(osmdroidActivity.class);
        View root = inflater.inflate(R.layout.fragment_terkep, container, false);
        osmdroidActivity.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        searchOnMap(getString(R.string.gym_search));
        return root;
    }*/

    /*private void searchOnMap(String option){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + option);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }*/
//}