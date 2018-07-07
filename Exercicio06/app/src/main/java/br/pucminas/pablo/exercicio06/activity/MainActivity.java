package br.pucminas.pablo.exercicio06.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.pucminas.pablo.exercicio06.R;
import br.pucminas.pablo.exercicio06.adapter.FilmeAdapter;
import br.pucminas.pablo.exercicio06.model.Filme;
import br.pucminas.pablo.exercicio06.model.FilmesDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.RV_filmes)
    RecyclerView RV_filmes;

    private List<Filme> listaFilmes = new ArrayList<>();
    private FilmeAdapter filmeAdapter;
    private DatabaseReference filmesFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        filmesFirebase = FilmesDatabase.getFirebaseDatabase();

        filmeAdapter = new FilmeAdapter(listaFilmes);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        RV_filmes.setLayoutManager(layoutManager);
        RV_filmes.setHasFixedSize(true);
        RV_filmes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        RV_filmes.setAdapter(filmeAdapter);

        filmesFirebase.orderByChild("votos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaFilmes.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Filme filme = dados.getValue(Filme.class);
                    listaFilmes.add(filme);
                }

                filmeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressLint("InflateParams")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_votar:
                Intent intent = new Intent(this, VotoActivity.class);
                startActivity(intent);
                break;
            case R.id.item_adicionar:
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                View view = layoutInflater.inflate(R.layout.dialog_inserir_filme, null);

                EditText ET_nomeFilme = view.findViewById(R.id.ET_nomeFilme);

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setView(view);
                dialog.setTitle("Adicionar um novo filme.");
                dialog.setPositiveButton("OK", (dialog1, which) -> adicionarFilme(ET_nomeFilme.getText().toString()));

                dialog.setNegativeButton("Cancelar", null);

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();


                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void adicionarFilme(String nomeFilme) {
        if (nomeFilme == null || nomeFilme.equals("")) {
            print("Obrigatório informar o nome do filme. Filme não cadastrado!");
        } else {
            final Filme filme = new Filme(nomeFilme);

            filmesFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean existeFilme = dataSnapshot.hasChild(filme.getId());

                    if (!existeFilme) {
                        filmesFirebase.child(filme.getId()).setValue(filme);
                        print("Filme cadastrado com sucesso!");
                    } else {
                        print("Filme já cadastrado!");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void print(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
