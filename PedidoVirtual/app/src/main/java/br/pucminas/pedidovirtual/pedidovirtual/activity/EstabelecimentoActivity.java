package br.pucminas.pedidovirtual.pedidovirtual.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.Utils.PersistenceUtil;
import br.pucminas.pedidovirtual.pedidovirtual.fragment.CardapioFragment;
import br.pucminas.pedidovirtual.pedidovirtual.fragment.EstabelecimentoFragment;
import br.pucminas.pedidovirtual.pedidovirtual.model.Estabelecimento;
import br.pucminas.pedidovirtual.pedidovirtual.model.Mesa;
import butterknife.ButterKnife;
import io.realm.Realm;

public class EstabelecimentoActivity extends AppCompatActivity {

    private static int CURRENT_FRAGMENT;
    private Estabelecimento estabelecimento;
    private Mesa mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);

        ButterKnife.bind(this);

        if (getIntent().hasExtra("qrcode")) {
            loadEstabelecimento();
            CURRENT_FRAGMENT = 1;
            loadFragment(new EstabelecimentoFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_close:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        backFragment();
    }


    public void nextFragment() {
        switch (CURRENT_FRAGMENT) {
            case 1:
                CURRENT_FRAGMENT = 2;
                loadFragment(new CardapioFragment());
                break;
            case 2:
                break;
        }
    }

    public void backFragment() {
        switch (CURRENT_FRAGMENT) {
            case 1:
                finish();
                break;
            case 2:
                CURRENT_FRAGMENT = 1;
                getSupportFragmentManager().popBackStack();
                break;
        }
    }


    private void loadFragment(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            fragment.setEnterTransition(new Slide(Gravity.END));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_fragment, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private void loadEstabelecimento() {
        Realm realm = Realm.getInstance(PersistenceUtil.realmConfig());
        mesa = realm.where(Mesa.class).equalTo("idQRCode", getIntent().getIntExtra("qrcode", 0)).findFirst();

        if (mesa != null) {
            estabelecimento = realm.where(Estabelecimento.class).equalTo("idEstabelecimento", mesa.getIdEstabelecimento()).findFirst();
        } else {
            Toast.makeText(this, R.string.erro_carregar_estabelecimento, Toast.LENGTH_LONG).show();
        }
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
