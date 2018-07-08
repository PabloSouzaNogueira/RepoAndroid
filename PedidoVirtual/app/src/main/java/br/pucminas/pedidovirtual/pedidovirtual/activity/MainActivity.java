package br.pucminas.pedidovirtual.pedidovirtual.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.Utils.NetworkUtil;
import br.pucminas.pedidovirtual.pedidovirtual.Utils.PersistenceUtil;
import br.pucminas.pedidovirtual.pedidovirtual.model.Estabelecimento;
import br.pucminas.pedidovirtual.pedidovirtual.model.Mesa;
import br.pucminas.pedidovirtual.pedidovirtual.model.Prato;
import br.pucminas.pedidovirtual.pedidovirtual.model.RequestEstabelecimento;
import br.pucminas.pedidovirtual.pedidovirtual.model.RequestPrato;
import br.pucminas.pedidovirtual.pedidovirtual.repository.EstabelecimentoRepository;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        loadQRCode(2);
    }

    @OnClick(R.id.TV_tirarFoto)
    void clickOnFoto() {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Escaneie o QRCode de sua mesa.");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                loadQRCode(Integer.parseInt(result.getContents()));
            } else {
                Toast.makeText(this, "Escaneamento cancelado.", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void loadQRCode(final int qrcode) {
        final Realm realm = Realm.getInstance(PersistenceUtil.realmConfig());

        if (NetworkUtil.isConnected(this)) {
            PersistenceUtil.repositoryPedidoVirtual().create(EstabelecimentoRepository.class).consultarQRCode(qrcode).enqueue(new Callback<RequestEstabelecimento>() {
                @Override
                public void onResponse(@NonNull Call<RequestEstabelecimento> call, final @NonNull Response<RequestEstabelecimento> response) {
                    if (response.body() != null) {
                        if (response.body().getSuccess() && response.body().getIdEstabelecimento() != 0) {

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(@NonNull Realm realm) {

                                    Estabelecimento estabelecimento = new Estabelecimento();
                                    estabelecimento.setIdEstabelecimento(response.body().getIdEstabelecimento());
                                    estabelecimento.setNome(response.body().getNome());
                                    estabelecimento.setLatitude(response.body().getLatitude());
                                    estabelecimento.setLongitude(response.body().getLongitude());
                                    estabelecimento.setRua(response.body().getRua());
                                    estabelecimento.setBairro(response.body().getBairro());
                                    estabelecimento.setNumero(response.body().getNumero());
                                    estabelecimento.setCidade(response.body().getCidade());
                                    estabelecimento.setEstado(response.body().getEstado());
                                    estabelecimento.setCEP(response.body().getCep());
                                    estabelecimento.setFoto(response.body().getFoto());

                                    Mesa mesa = realm.where(Mesa.class).equalTo("idQRCode", qrcode).findFirst();

                                    if (mesa == null) {
                                        mesa = new Mesa();
                                        mesa.setIdQRCode(qrcode);
                                        mesa.setIdEstabelecimento(response.body().getIdEstabelecimento());
                                        mesa.setMesa(response.body().getMesa());
                                        realm.copyToRealmOrUpdate(mesa);
                                    }

                                    RealmList<Prato> realmListPrato = new RealmList<>();

                                    for (RequestPrato requestPrato : response.body().getRequestPratos()) {
                                        Prato prato = new Prato();
                                        prato.setIdPrato(requestPrato.getIdPrato());
                                        prato.setNome(requestPrato.getNome());
                                        prato.setDescricao(requestPrato.getDescricao());
                                        prato.setFoto(requestPrato.getFoto());
                                        realmListPrato.add(prato);
                                    }

                                    estabelecimento.setPratos(realmListPrato);
                                    realm.copyToRealmOrUpdate(estabelecimento);

                                    Intent intent = new Intent(MainActivity.this, EstabelecimentoActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            if (response.body().getMessage() != null)
                                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RequestEstabelecimento> call, @NonNull Throwable t) {

                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, R.string.habilitar_internet, Toast.LENGTH_LONG).show();
            //carregar realm
        }

    }
}




