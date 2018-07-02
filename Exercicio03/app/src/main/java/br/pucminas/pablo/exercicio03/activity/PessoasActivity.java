package br.pucminas.pablo.exercicio03.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.pucminas.pablo.exercicio03.Persistencia;
import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.adapter.PessoaAdapter;
import br.pucminas.pablo.exercicio03.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PessoasActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.RV_pessoas)
    RecyclerView RV_pessoas;

    //List<Pessoa> pessoas = new ArrayList<>();
    PessoaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        adapter = new PessoaAdapter(Persistencia.getPessoas(), this);

        RV_pessoas.setLayoutManager(new LinearLayoutManager(this));
        RV_pessoas.setHasFixedSize(true);
        RV_pessoas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        RV_pessoas.setAdapter(adapter);
    }


    @OnClick(R.id.fab)
    void clickOnAdd() {
        Intent intent = new Intent(this, AddPessoaActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            if(bundle != null) {
                Pessoa pessoa = (Pessoa) bundle.getSerializable("pessoa");
                Persistencia.getPessoas().add(pessoa);

            }

        }
    }
}
