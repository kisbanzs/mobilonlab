package hu.bme.aut.vizivandor.ui.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import hu.bme.aut.vizivandor.R;


public class MyListAdapter extends FirebaseRecyclerAdapter<MyListData, MyListAdapter.ViewHolder> {
    //private MyListData[] listdata;


    public MyListAdapter(@NonNull FirebaseRecyclerOptions<MyListData> options){
        super(options);
    }

    // RecyclerView recyclerView;
    /*public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_post_new_post_card_view, parent, false);
        //ViewHolder viewHolder = new ViewHolder(listItem);
        return new MyListAdapter.ViewHolder(listItem);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position, @NonNull MyListData model) {
        //final MyListData myListData = model;
        holder.textTitle.setText(model.getTitle());
        holder.textDesc.setText(model.getDescription());
        holder.textUser.setText(model.getUsername());

        //holder.imageView.setImageResource(listdata[position].getImgId());
        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });*/
    }


   /* @Override
    public int getItemCount() {
        return listdata.length;
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView imageView;
        public TextView textTitle;
        public TextView textDesc;
        public TextView textUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textTitle = (TextView) itemView.findViewById(R.id.post_title_txtview);
            this.textDesc = (TextView) itemView.findViewById(R.id.post_desc_txtview);
            this.textUser = (TextView) itemView.findViewById(R.id.post_user);

        }
    }
}

