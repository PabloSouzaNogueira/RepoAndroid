package br.pucminas.pablo.exercicio03.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.model.Consumo;
import br.pucminas.pablo.exercicio03.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddConsumoActivity extends AppCompatActivity {

    @BindView(R.id.ET_nome)
    TextInputEditText ET_nome;
    @BindView(R.id.ET_valorUnitario)
    TextInputEditText ET_valorUnitario;
    @BindView(R.id.ET_quantidade)
    TextInputEditText ET_quantidade;
    @BindView(R.id.TV_valorTotal)
    TextView TV_valorTotal;

    Consumo consumo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumo);

        ButterKnife.bind(this);

        consumo = new Consumo();

        ET_valorUnitario.addTextChangedListener(watcher());
        ET_quantidade.addTextChangedListener(watcher());
    }


    private TextWatcher watcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (ET_valorUnitario.getText().toString().equals("") || ET_quantidade.getText().toString().equals("")) {
                    TV_valorTotal.setText(R.string.zero);
                } else {
                    consumo.setQuantidade(Integer.parseInt(ET_quantidade.getText().toString()));
                    consumo.setValorUnitario(Double.parseDouble(ET_valorUnitario.getText().toString()));
                    consumo.setValorTotal(consumo.getQuantidade() * consumo.getValorUnitario());

                    Locale brasil = new Locale("pt", "BR");
                    TV_valorTotal.setText(String.format(brasil,"R$ %.2f",consumo.getValorTotal()));
                }

            }
        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_close:
                onBackPressed();
                break;
            case R.id.action_confirm:
                confirmAdd();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void confirmAdd() {
        if (ET_nome == null || ET_nome.getText().toString().equals("")) {
            Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        if (ET_valorUnitario == null || ET_valorUnitario.getText().toString().equals("")) {
            Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        if (ET_quantidade == null || ET_quantidade.getText().toString().equals("")) {
            Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        if (consumo.getValorTotal() <= 0) {
            Toast.makeText(this, "O valor total não pode ser negativo ou ZERO.", Toast.LENGTH_LONG).show();
            return;
        }

        consumo.setNome(ET_nome.getText().toString());

        Intent intent = getIntent();
        intent.putExtra("consumo", consumo);
        setResult(RESULT_OK, intent);
        finish();
    }
}


