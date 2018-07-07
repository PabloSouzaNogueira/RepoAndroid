package br.pucminas.pablo.exercicio05.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import br.pucminas.pablo.exercicio05.Adapter.NotaAdapter;
import br.pucminas.pablo.exercicio05.Model.Nota;
import br.pucminas.pablo.exercicio05.NotasDatabase;
import br.pucminas.pablo.exercicio05.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.MSV_search)
    MaterialSearchView MSV_search;
    @BindView(R.id.RV_notas)
    RecyclerView RV_notas;


    private NotasDatabase notasDatabase;
    private NotaAdapter adapter;

    private List<Nota> notas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        notasDatabase = NotasDatabase.getDatabase(this);

        RV_notas.setLayoutManager(new LinearLayoutManager(this));
        RV_notas.setHasFixedSize(true);

        MSV_search.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pesquisarNotas(newText);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarNotas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.item_search);
        MSV_search.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_search) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void clickOnAdd() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    private void pesquisarNotas(String titulo) {
        notas = notasDatabase.notaDAO().findByTitulo(titulo);
        adapter = new NotaAdapter(notas, this);
        adapter.notifyDataSetChanged();
        RV_notas.setAdapter(adapter);
    }

    public void carregarNotas() {
        try {
            notas = notasDatabase.notaDAO().getAll();
            adapter = new NotaAdapter(notas, this);
            RV_notas.setAdapter(adapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
