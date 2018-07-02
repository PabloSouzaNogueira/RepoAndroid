package br.pucminas.pablo.exercicio03.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.pucminas.pablo.exercicio03.Persistencia;
import br.pucminas.pablo.exercicio03.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.BNV_main)
    BottomNavigationView BNV_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BNV_main.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        Persistencia persistencia = new Persistencia();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_person:
                    Intent intentPessoa = new Intent(MainActivity.this, PessoasActivity.class);
                    startActivity(intentPessoa);
                    return true;
                case R.id.nav_restaurant:
                    Intent intentConsumo = new Intent(MainActivity.this, ConsumosActivity.class);
                    startActivity(intentConsumo);
                    return true;
                case R.id.nav_money:
                    Intent intentTaxaServico = new Intent(MainActivity.this, TaxaServicoActivity.class);
                    startActivity(intentTaxaServico);
                    return true;
            }
            return false;
        }
    };


}
