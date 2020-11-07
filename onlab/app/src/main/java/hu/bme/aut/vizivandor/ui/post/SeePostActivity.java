package hu.bme.aut.vizivandor.ui.post;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hu.bme.aut.vizivandor.R;

public class SeePostActivity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_see_post_cards);

        listview = findViewById(R.id.listView);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listitem, list);
        listview.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("VizivandorFirebase");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list.clear();
                for(DataSnapshot snapshot: datasnapshot.getChildren()){

                    VizivandorFirebase vf = snapshot.getValue(VizivandorFirebase.class);
                    String txt = vf.getTitle() + " : " + vf.getDescription();
                    list.add(txt);
                    System.out.println(vf.getDescription());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
