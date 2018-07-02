package br.pucminas.pablo.exercicio03.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.pucminas.pablo.exercicio03.Persistencia;
import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.adapter.ConsumoAdapter;
import br.pucminas.pablo.exercicio03.model.Consumo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumosActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.RV_consumos)
    RecyclerView RV_consumos;

    List<Consumo> consumos = new ArrayList<>();
    ConsumoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumos);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        adapter = new ConsumoAdapter(Persistencia.getConsumos(), this);

        RV_consumos.setLayoutManager(new LinearLayoutManager(this));
        RV_consumos.setHasFixedSize(true);
        RV_consumos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        RV_consumos.setAdapter(adapter);

    }

    @OnClick(R.id.fab)
    void clickOnAdd() {
        Intent intent = new Intent(this, AddConsumoActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            if(bundle != null) {
                Consumo consumo = (Consumo) bundle.getSerializable("consumo");
                Persistencia.getConsumos().add(consumo);
            }
        }
    }

}
