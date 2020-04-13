package hu.bme.aut.vizivandor.ui.tura;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import hu.bme.aut.vizivandor.R;

public class TuraFragment extends Fragment {

    private TuraViewModel turaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        turaViewModel =
                ViewModelProviders.of(this).get(TuraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tura, container, false);



        ImageButton FELSOTISZA = root.findViewById(R.id.btnFelsoTisza);
        ImageButton TOKAJ = root.findViewById(R.id.btnTokajBodrog);
        ImageButton DUNAKANYAR = root.findViewById(R.id.btnDunakanyar);
        ImageButton SZIGETKOZ = root.findViewById(R.id.btnSzigetkoz);
        ImageButton ALSODUNA = root.findViewById(R.id.btnAlsoDuna);
        ImageButton KOROS = root.findViewById(R.id.btnKoros);
        ImageButton TISZATO = root.findViewById(R.id.btnTiszaTo);



        FELSOTISZA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(getActivity(), ListTuraActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}