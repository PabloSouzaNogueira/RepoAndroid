package br.pucminas.pedidovirtual.pedidovirtual.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import butterknife.OnClick;

public class EstabelecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void voltar() {



    }
}
