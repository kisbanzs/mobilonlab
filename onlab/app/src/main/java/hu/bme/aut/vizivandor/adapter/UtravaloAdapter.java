package hu.bme.aut.vizivandor.adapter;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.data.UtravaloListaDatabase;
import hu.bme.aut.vizivandor.data.UtravaloListaItem;


public class UtravaloAdapter
        extends RecyclerView.Adapter<UtravaloAdapter.UtravaloViewHolder> {

    private List<UtravaloListaItem> utravaloLista;
    private UtravaloListaDatabase database;

    public UtravaloAdapter(UtravaloListaDatabase database) {
        this.database = database;
        this.utravaloLista = new ArrayList<UtravaloListaItem>();
    }


    @NonNull
    @Override
    public UtravaloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_tura_felsorolt_lista, parent, false);
        return new UtravaloViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UtravaloViewHolder holder, final int position) {
        final UtravaloListaItem item = utravaloLista.get(position);
        holder.listaelem.setText(item.targynev);
        holder.listatorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeUtravalo(item.id);
            }
        });

        holder.vanvagynincs.setOnClickListener(new View.OnClickListener() {
            boolean isChecked;
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    isChecked = true;
                }
                else {
                    isChecked = false;
                }
                item.checkbox = isChecked;
                updateUtravalo(position, item);
            }
        });

        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return utravaloLista.size();
    }

    public void setUtravaloLista(List<UtravaloListaItem> utravalok) {
        this.utravaloLista = utravalok;
        notifyDataSetChanged();
    }

    public interface UtravaloClickListener {
        void onItemChanged(UtravaloListaItem item);
    }


    public void addUtravalo(final UtravaloListaItem utravalo) {
        new AsyncTask<Void, Void, UtravaloListaItem>() {

            @Override
            protected UtravaloListaItem doInBackground(Void... voids) {
                utravalo.id = database.utravaloListaItemDao().insert(utravalo);
                return utravalo;
            }

            @Override
            protected void onPostExecute(UtravaloListaItem utravalo) {
                utravaloLista.add(0, utravalo);
                notifyItemInserted(0);
            }
        }.execute();
    }

    public void updateUtravalo(final int index, final UtravaloListaItem utravalo) {
        new AsyncTask<Void, Void, Boolean>() {


            @Override
            protected Boolean doInBackground(Void... voids) {
                database.utravaloListaItemDao().update(utravalo);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                utravaloLista.set(index, utravalo);
                notifyItemChanged(index);
            }
        }.execute();
    }

    public void removeUtravalo(final long id) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                UtravaloListaItem item = database.utravaloListaItemDao().loadById(id);
                if(item != null){
                    database.utravaloListaItemDao().deleteItem(item);
                    return true;
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                deleteItem(id);
            }
        }.execute();

    }


    public void deleteItem(long id){
        int index = 0;
        UtravaloListaItem itemToRemove = null;
        for(UtravaloListaItem item : utravaloLista) {
            if (item.id == id){
                itemToRemove = item;
                break;
            }
            index++;
        }

        if(itemToRemove != null) {
            utravaloLista.remove(itemToRemove);
            notifyItemRemoved(index);
        }
    }

    public UtravaloListaItem getUtravalo(int index) {
        return utravaloLista.get(index);
    }

    class UtravaloViewHolder extends RecyclerView.ViewHolder {

        UtravaloListaItem item;

        //private ToolsViewModel toolsViewModel;
        private TextView listaelem;
        private Button listatorles;
        private CheckBox vanvagynincs;

        UtravaloViewHolder(View itemView) {
            super(itemView);
            listaelem = itemView.findViewById(R.id.lista_neve);
            listatorles = itemView.findViewById(R.id.lista_delete_button);
            vanvagynincs = itemView.findViewById(R.id.checkbox_lista);
        }


    }

}