/*package hu.bme.aut.vizivandor.ui.send;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.bme.aut.vizivandor.R;

public class SendActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_send);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        Toast.makeText(this,
                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();
    }

    public void showNumberPicker(View view){
        NumberPickerDialog newFragment = new NumberPickerDialog();
        newFragment.setValueChangeListener(this);
        newFragment.show(getSupportFragmentManager(), "time picker");
    }
}
*/