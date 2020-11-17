package hu.bme.aut.vizivandor.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hu.bme.aut.vizivandor.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    //létszám számláló
    private NumberPicker picker1;
    private Integer[] pickerVals;
    //email küldés
    private EditText eTo;
    private EditText eSubject;
    private EditText eNev;
    private NumberPicker eLetszam;
    private Spinner eTurahely;
    private EditText eMsg;
    private ImageButton btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        //final TextView textView = root.findViewById(R.id.text_send);


       /* sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        picker1 = root.findViewById(R.id.numberpicker_main_picker);

        if (picker1 != null) {
            picker1.setMinValue(1);
            picker1.setMaxValue(25);
            pickerVals  = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
            picker1.setWrapSelectorWheel(true);
            picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    int valuePicker1 = picker1.getValue();
                    //Log.d("picker value", pickerVals);
                }
            });
        }

        String [] values =
                {"A Felső-Tisza","Tokaj és a Bodrogzug","Dunakanyar túra","A Szigetköz szépségei","Vízivándor tábor az Alsó-Dunán","Vízivándor túra a Körösökön","Vízivándor tábor a Tisza-tavon"};
        Spinner spinner = (Spinner) root.findViewById(R.id.turak);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        //eTo = (EditText)findViewById(R.id.txtTo);
        //eSubject = (EditText)findViewById(R.id.txtSub);
        eNev = (EditText)root.findViewById(R.id.text_send);
        eLetszam = (NumberPicker)root.findViewById(R.id.numberpicker_main_picker);
        eTurahely = (Spinner)root.findViewById(R.id.turak);
        eMsg = (EditText)root.findViewById(R.id.txtMsg);
        btn = (ImageButton)root.findViewById(R.id.btnSend);

        if( eNev.getText().toString().length() == 0 )
            eNev.setError( "Kérem adja meg a nevét" );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{"zsofi.kisban@gmail.com"});
                it.putExtra(Intent.EXTRA_SUBJECT, "Vízivándor jelentkezés");
                it.putExtra(Intent.EXTRA_TEXT,"Vízivándor táborra jelentkezem "
                        + eLetszam.getValue() + "db fővel \n\n"
                        + String.valueOf(eTurahely.getSelectedItem()) + " túrahelyre. \n"
                        + "Egyéb megjegyzésem: " + eMsg.getText() + "\n\n"
                        + "Üdvözlettel: \n" + eNev.getText());
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Choose Mail App"));
            }
        });


       /* picker1.setMaxValue(5);
        picker1.setMinValue(1);
        pickerVals  = new String[] {"dog", "cat", "lizard", "turtle", "axolotl"};
        picker1.setDisplayedValues(pickerVals);

        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = picker1.getValue();
                Log.d("picker value", pickerVals[valuePicker1]);
            }
        });*/

        return root;
    }


}