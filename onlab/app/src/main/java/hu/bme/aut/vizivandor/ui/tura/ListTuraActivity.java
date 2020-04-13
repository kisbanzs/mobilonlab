package hu.bme.aut.vizivandor.ui.tura;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import hu.bme.aut.vizivandor.R;

public class ListTuraActivity extends AppCompatActivity {

    public static final String KEY_TURA_TYPE = "KEY_TURA_TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turautvonal_informaciok);

        Intent intent = getIntent();
        final int turaType = intent.getIntExtra(KEY_TURA_TYPE, -1);


        TextView tvTuraType = findViewById(R.id.tura_neve);
        tvTuraType.setText(getTypeString(turaType));




}

    private String getTypeString(int turaType) {
        switch (turaType) {
            case TuraFragment.TYPE_FELSOTISZA:
                return "A Felső-Tisza";
            case TuraFragment.TYPE_TOKAJ:
                return "Tokaj és a Bodrogzug";
            case TuraFragment.TYPE_DUNAKANYAR:
                return "Dunakanyar túra";
            case TuraFragment.TYPE_SZIGETKOZ:
                return "A Szigetköz szépségei";
            case TuraFragment.TYPE_ALSODUNA:
                return "Vízivándor tábor az Alsó-Dunán";
            case TuraFragment.TYPE_KOROS:
                return "Vízivándor túra a Körösökön";
            case TuraFragment.TYPE_TISZATO:
                return "Vízivándor tábor a Tisza-tavon";
            default:
                return "NINCS ILYEN TURA";
        }
    }


}