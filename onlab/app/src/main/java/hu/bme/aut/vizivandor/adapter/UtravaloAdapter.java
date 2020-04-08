package hu.bme.aut.vizivandor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.data.UtravaloListaDatabase;
import hu.bme.aut.vizivandor.data.UtravaloListaItem;
import hu.bme.aut.vizivandor.ui.utravalo.ToolsViewModel;


public class UtravaloAdapter
        extends RecyclerView.Adapter<UtravaloAdapter.UtravaloViewHolder> {

    private final List<UtravaloListaItem> items;
    private UtravaloListaDatabase database;
    private UtravaloClickListener listener;

    public UtravaloAdapter(UtravaloClickListener listener, UtravaloListaDatabase database) {
        this.listener = listener;
        this.database = database;
        items = new ArrayList<>();
    }


    @NonNull
    @Override
    public UtravaloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.felsorolt_lista, parent, false);
        return new UtravaloViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UtravaloViewHolder holder, int position) {
        final UtravaloListaItem item = items.get(position);
        holder.ujlistaelem.setText(item.targynev);
        /*holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteRaceEventsItem(item.id);

            }
        });*/
        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface UtravaloClickListener {
        void onItemChanged(UtravaloListaItem item);
    }


    class UtravaloViewHolder extends RecyclerView.ViewHolder {

        UtravaloListaItem item;

        private ToolsViewModel toolsViewModel;
        private TextView listaelemek;
        private EditText ujlistaelem;
        private Button listahozzadas;
        private CheckBox vanvagynincs;

        UtravaloViewHolder(View itemView) {
            super(itemView);

            listaelemek = itemView.findViewById(R.id.lista_neve);
            ujlistaelem = itemView.findViewById(R.id.aktualis_elem);
            listahozzadas = itemView.findViewById(R.id.listasave_button);
            vanvagynincs = itemView.findViewById(R.id.checkbox_lista);


        }


    }

}
