package hu.bme.aut.vizivandor.ui.toplista;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import hu.bme.aut.vizivandor.R;

public class ToplistaFragment extends Fragment {

    private RecyclerView recyclerView;
    MyToplistaAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_toplista, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.toplista);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<MyToplistaData> myToplistaData
                = new FirebaseRecyclerOptions.Builder<MyToplistaData>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("VizivandorToplista"), MyToplistaData.class)
                .build();

        adapter = new MyToplistaAdapter(myToplistaData);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }


    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }


}
