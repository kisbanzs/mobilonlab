package hu.bme.aut.vizivandor.ui.utravalo;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private Button torles;

    //adatbázis
    private RecyclerView recyclerView;
    private UtravaloAdapter utravaloadapter;
    private UtravaloListaDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = Room.databaseBuilder(
                getActivity().getApplicationContext(),
                UtravaloListaDatabase.class,
                "utravalo-list"
        ).build();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_utravalo, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaelemek = view.findViewById(R.id.lista_neve);
        ujlistaelem = view.findViewById(R.id.aktualis_elem);
        listahozzadas = view.findViewById(R.id.listasave_button);
        vanvagynincs = view.findViewById(R.id.checkbox_lista);
        torles = view.findViewById(R.id.lista_delete_button);

        listahozzadas.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                if (ujlistaelem.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Nincs minden mezo kitoltve!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                UtravaloListaItem listaElem = getUtravaloListaItem();

                createUtravaloListaElem(listaElem);
                ujlistaelem.setText("");

            }
        });
       /* if(){
            torles.setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {

                    UtravaloListaItem listaElem = getUtravaloListaItem();

                    deleteUtravaloListaElem(listaElem);
                }
            });
        }*/

        recyclerView = (RecyclerView) view.findViewById(R.id.lista_elemek);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        utravaloadapter = new UtravaloAdapter(database);
        recyclerView.setAdapter(utravaloadapter);

        loadItemsInBackground();

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

            @Override
            protected void onPostExecute(List<UtravaloListaItem> utravalok) {
                utravaloadapter.setUtravaloLista(utravalok);
            }
        }.execute();
    }

    public void createUtravaloListaElem(final UtravaloListaItem newItem) {
        utravaloadapter.addUtravalo(newItem);
    }

   /* public void deleteUtravaloListaElem(final UtravaloListaItem removeItem) {
        int index = removeItem.id;
        utravaloadapter.removeUtravalo(index);
    }*/


}