package hu.bme.aut.vizivandor.ui.utravalo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.adapter.UtravaloAdapter;
import hu.bme.aut.vizivandor.data.UtravaloListaDatabase;
import hu.bme.aut.vizivandor.data.UtravaloListaItem;

public class UtravaloFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private TextView listaelemek;
    private EditText ujlistaelem;
    private Button listahozzadas;
    private CheckBox vanvagynincs;

    //adatbázis
    private RecyclerView recyclerView;
    private UtravaloAdapter utravaloadapter;
    private UtravaloListaDatabase database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_utravalo, container, false);


        listaelemek = root.findViewById(R.id.lista_neve);
        ujlistaelem = root.findViewById(R.id.aktualis_elem);
        listahozzadas = root.findViewById(R.id.listasave_button);
        vanvagynincs = root.findViewById(R.id.checkbox_lista);


        listahozzadas.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                if (ujlistaelem.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Nincs minden mezo kitoltve!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                UtravaloListaItem lista = getUtravaloListaItem();

                createUtralaoLista(lista);
                ujlistaelem.setText("");

            }
        });

        /*final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/



        database = Room.databaseBuilder(
                getActivity().getApplicationContext(),
                UtravaloListaDatabase.class,
                "utravalo-list"
        ).build();


        return root;
    }

    private UtravaloListaItem getUtravaloListaItem() {
        UtravaloListaItem utravalolistaelem = new UtravaloListaItem();


        utravalolistaelem.targynev = ujlistaelem.getText().toString();
        return utravalolistaelem;
    }


    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<UtravaloListaItem>>() {

            @Override
            protected List<UtravaloListaItem> doInBackground(Void... voids) {
                return database.utravaloListaItemDao().getAll();
            }

            /*@Override
            protected void onPostExecute(List<UtravaloListaItem> shoppingItems) {
                utravaloadapter.update(shoppingItems);
            }*/
        }.execute();
    }

   /* @Override
    public void onItemChanged(final UtravaloListaItem item) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.utravaloListaItemDao().update(item);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "ShoppingItem update was successful");
            }
        }.execute();
    }*/

    public void createUtralaoLista(final UtravaloListaItem newItem) {
        new AsyncTask<Void, Void, UtravaloListaItem>() {

            @Override
            protected UtravaloListaItem doInBackground(Void... voids) {
                newItem.id = database.utravaloListaItemDao().insert(newItem);
                return newItem;
            }

            /*@Override
            protected void onPostExecute(UtravaloListaItem shoppingItem) {
                eventsAdapter.addItem(shoppingItem);
            }*/
        }.execute();
        }




}