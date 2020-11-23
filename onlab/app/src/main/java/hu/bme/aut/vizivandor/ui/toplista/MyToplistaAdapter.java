package hu.bme.aut.vizivandor.ui.toplista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.ui.post.MyListAdapter;


public class MyToplistaAdapter extends FirebaseRecyclerAdapter<MyToplistaData, MyToplistaAdapter.ViewHolder> {

    public MyToplistaAdapter(@NonNull FirebaseRecyclerOptions<MyToplistaData> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MyToplistaData model) {
        holder.textDistance.setText(model.getDistance());
        holder.textUser.setText(model.getUsername());
    }


    @Override
    public MyToplistaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_toplista_cardview, parent, false);
        return new MyToplistaAdapter.ViewHolder(listItem);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textUser;
        public TextView textDistance;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textUser = (TextView) itemView.findViewById(R.id.textViewUsername);
            this.textDistance = (TextView) itemView.findViewById(R.id.textViewMegtettut);

        }

    }




}
