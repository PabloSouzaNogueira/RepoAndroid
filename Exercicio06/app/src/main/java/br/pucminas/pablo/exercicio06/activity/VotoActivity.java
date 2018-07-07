package br.pucminas.pablo.exercicio06.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.pucminas.pablo.exercicio06.R;
import br.pucminas.pablo.exercicio06.adapter.FilmeVotoAdapter;
import br.pucminas.pablo.exercicio06.model.Filme;
import br.pucminas.pablo.exercicio06.model.FilmesDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VotoActivity extends AppCompatActivity {

    @BindView(R.id.RV_filmesVotar)
    RecyclerView RV_filmesVotar;

    private FilmeVotoAdapter filmeVotoAdapter;
    private List<Filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voto);

        ButterKnife.bind(this);

        DatabaseReference filmesFirebase = FilmesDatabase.getFirebaseDatabase();

        filmeVotoAdapter = new FilmeVotoAdapter(listaFilmes, this);

        RV_filmesVotar.setLayoutManager(new LinearLayoutManager(this));
        RV_filmesVotar.setHasFixedSize(true);
        RV_filmesVotar.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        RV_filmesVotar.setAdapter(filmeVotoAdapter);


        filmesFirebase.orderByChild("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaFilmes.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Filme filme = dados.getValue(Filme.class);
                    listaFilmes.add(filme);
                }

                filmeVotoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
