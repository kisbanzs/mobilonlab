package hu.bme.aut.vizivandor.ui.send;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
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
    private NumberPicker picker1;
    private Integer[] pickerVals;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);


        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


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

   // private NumberPicker picker1;
   // private String[] pickerVals;
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picker1 = findViewById(R.id.numberpicker_main_picker);
        picker1.setMaxValue(500);
        picker1.setMinValue(1);
        pickerVals  = new String[] {"dog", "cat", "lizard", "turtle", "axolotl"};
        picker1.setDisplayedValues(pickerVals);

        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = picker1.getValue();
                Log.d("picker value", pickerVals[valuePicker1]);
            }
        });

    }*/

}