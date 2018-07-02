package br.pucminas.pablo.exercicio03.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPessoaActivity extends AppCompatActivity {

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    @BindView(R.id.ET_nome)
    TextInputEditText ET_nome;
    @BindView(R.id.IV_foto)
    ImageView IV_foto;
    @BindView(R.id.IV_addFoto)
    ImageView IV_addFoto;

    private Bitmap bitmap;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pessoa);

        ButterKnife.bind(this);

       // setSupportActionBar(toolbar);
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


    @OnClick(R.id.IV_addFoto)
    void onClickAddFoto() {

        PackageManager PM = this.getPackageManager();

        if (PM.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                IV_addFoto.setEnabled(false);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }


        } else {
            Toast.makeText(this, "Não é possível acessar a câmera!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                bitmap = (Bitmap) bundle.get("data");
                IV_foto.setImageBitmap(bitmap);
            }
        }
    }

    private void confirmAdd() {
        if (ET_nome == null || ET_nome.getText().toString().equals("")) {
            Toast.makeText(this, "Favor informar um nome para pessoa.", Toast.LENGTH_LONG).show();
            return;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(ET_nome.getText().toString());

        if (bitmap != null) {
            ByteArrayOutputStream saida = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
            byte[] image = saida.toByteArray();
            pessoa.setFoto(image);
        }

        Intent intent = getIntent();
        intent.putExtra("pessoa", pessoa);
        setResult(RESULT_OK,intent);
        finish();
    }
}
