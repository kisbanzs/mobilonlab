package hu.bme.aut.vizivandor.ui.post;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hu.bme.aut.vizivandor.R;

public class SeePostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListView listview;
    private TextView title;
    private TextView desc;
    private TextView username;
    MyListAdapter adapter;

    private String post_key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_see_post_cards);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MyListData> myListData
                = new FirebaseRecyclerOptions.Builder<MyListData>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("VizivandorFirebase"), MyListData.class)
                .build();

        adapter = new MyListAdapter(myListData);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SeePostActivity.this));
        recyclerView.setAdapter(adapter);



    }


    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }


    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



}
