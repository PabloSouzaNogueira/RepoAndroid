package br.pucminas.pablo.exercicio07;

import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ET_cep)
    EditText ET_cep;
    @BindView(R.id.BT_pesquisar)
    Button BT_pesquisar;
    @BindView(R.id.ET_logradouro)
    TextInputEditText ET_logradouro;
    @BindView(R.id.ET_complemento)
    TextInputEditText ET_complemento;
    @BindView(R.id.ET_bairro)
    TextInputEditText ET_bairro;
    @BindView(R.id.ET_localidade)
    TextInputEditText ET_localidade;
    @BindView(R.id.ET_estado)
    TextInputEditText ET_estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    public void print(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.BT_pesquisar)
    void clickOnPersquisar() {

        String cep = ET_cep.getText().toString();

        if (cep.equals("")) {
            print("Obrigatório informar o CEP!");
        } else {
            WebServiceEndereco webServiceEndereco = new WebServiceEndereco();
            webServiceEndereco.execute(cep);
        }
    }

    public class WebServiceEndereco extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("https://viacep.com.br/ws/" + strings[0] + "/json/");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String linha;
                StringBuilder buffer = new StringBuilder();
                while ((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");
                }

                return buffer.toString();

            } catch (Exception ex) {
                ex.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) print("Não foi possível recuperar os dados...");
            else {
                try {

                    JSONObject json = new JSONObject(s);

                    ET_logradouro.setText(json.getString("logradouro"));
                    ET_complemento.setText(json.getString("complemento"));
                    ET_bairro.setText(json.getString("bairro"));
                    ET_localidade.setText(json.getString("localidade"));
                    ET_estado.setText(json.getString("uf"));

                    print("Endereço recuperado com sucesso!");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}