package br.pucminas.pablo.exercicio05.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import br.pucminas.pablo.exercicio05.Model.Nota;
import br.pucminas.pablo.exercicio05.NotasDatabase;
import br.pucminas.pablo.exercicio05.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.ET_tituloNota)
    EditText ET_tituloNota;
    @BindView(R.id.ET_textoNota)
    EditText ET_textoNota;


    private NotasDatabase notasDatabase;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        notasDatabase = NotasDatabase.getDatabase(this);

        nota = (Nota) getIntent().getSerializableExtra("nota");

        if (nota != null) {
            ET_tituloNota.setText(nota.getTitulo());
            ET_textoNota.setText(nota.getTexto());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                if (verificarCampos()) {
                    if (nota != null) {
                        atualizarNota();
                    } else {
                        inserirNota();
                    }
                    onBackPressed();
                } else {
                    Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.item_close:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void atualizarNota() {
        nota.setTitulo(ET_tituloNota.getText().toString());
        nota.setTexto(ET_textoNota.getText().toString());
        notasDatabase.notaDAO().update(nota);
        Toast.makeText(this, "Nota atualizada com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void inserirNota() {
        Nota nota = new Nota();
        nota.setTitulo(ET_tituloNota.getText().toString());
        nota.setTexto(ET_textoNota.getText().toString());
        notasDatabase.notaDAO().insert(nota);
        Toast.makeText(this, "Nota cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private boolean verificarCampos() {
        return !ET_tituloNota.getText().toString().equals("") && !ET_textoNota.getText().toString().equals("");
    }

}
