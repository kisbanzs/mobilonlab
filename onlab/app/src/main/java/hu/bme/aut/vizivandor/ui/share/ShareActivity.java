/*package hu.bme.aut.vizivandor.ui.share;

import hu.bme.aut.vizivandor.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class ShareActivity extends AppCompatActivity {

    public static final String KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_share);

        Intent intent = getIntent();
        final int transportType = intent.getIntExtra(KEY_TRANSPORT_TYPE, -1);


        TextView tvTicketType = findViewById(R.id.tvTicketType);
        tvTicketType.setText(getTypeString(transportType));

        final DatePicker dpStartDate = findViewById(R.id.dpStartDate);
        final DatePicker dpEndDate = findViewById(R.id.dpEndDate);
        final Button btnPurchase = findViewById(R.id.btnPurchase);

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String typeString = getTypeString(transportType);
                String dateString = getDateFrom(dpStartDate) + " - " + getDateFrom(dpEndDate);

                Intent intent = new Intent(ShareActivity.this, PassActivity.class);
                intent.putExtra(PassActivity.KEY_TYPE_STRING, typeString);
                intent.putExtra(PassActivity.KEY_DATE_STRING, dateString);
                startActivity(intent);
            }
        });

    }

    private String getTypeString(int transportType) {
        switch (transportType) {
            case ListActivity.TYPE_BUS:
                return "Bus pass";
            case ListActivity.TYPE_TRAIN:
                return "Train pass";
            case ListActivity.TYPE_BIKE:
                return "Bike pass";
            case ListActivity.TYPE_BOAT:
                return "Boat pass";
            default:
                return "Unknown pass type";
        }
    }

    private String getDateFrom(final DatePicker picker) {
        return String.format(Locale.getDefault(), "%04d.%02d.%02d.",
                picker.getYear(), picker.getMonth() + 1, picker.getDayOfMonth());
    }


}
*/
