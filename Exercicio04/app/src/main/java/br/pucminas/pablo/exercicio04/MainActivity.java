package br.pucminas.pablo.exercicio04;

import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.LL_container)
    LinearLayoutCompat LL_container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.RB_vermelho)
    RadioButton RB_vermelho;
    @BindView(R.id.RB_azul)
    RadioButton RB_azul;
    @BindView(R.id.RB_verde)
    RadioButton RB_verde;
    @BindView(R.id.RB_amarelo)
    RadioButton RB_amarelo;
    @BindView(R.id.RG_cores)
    RadioGroup RG_cores;


    private static final String PREFERENCES = "NomeArquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final SharedPreferences preferences = getSharedPreferences(PREFERENCES, 0);

        if (preferences.contains("OpcaoCor")) {
            setarCor(preferences.getInt("OpcaoCor", 0));
        }

        RG_cores.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor editor = preferences.edit();

                switch (RG_cores.getCheckedRadioButtonId()) {
                    case R.id.RB_vermelho:
                        setarCor(1);
                        editor.putInt("OpcaoCor", 1);
                        break;
                    case R.id.RB_azul:
                        setarCor(2);
                        editor.putInt("OpcaoCor", 2);
                        break;
                    case R.id.RB_verde:
                        setarCor(3);
                        editor.putInt("OpcaoCor", 3);
                        break;
                    case R.id.RB_amarelo:
                        setarCor(4);
                        editor.putInt("OpcaoCor", 4);
                        break;
                }

                editor.apply();
            }
        });
    }

    private void setarCor(int cor) {
        switch (cor) {
            case 1:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorvermelho));
                LL_container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorvermelhoclaro));
                RB_vermelho.setChecked(true);
                break;
            case 2:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorazul));
                LL_container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorazulclaro));
                RB_azul.setChecked(true);
                break;
            case 3:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorverde));
                LL_container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorverdeclaro));
                RB_verde.setChecked(true);
                break;
            case 4:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.coloramarelo));
                LL_container.setBackgroundColor(ContextCompat.getColor(this, R.color.coloramareloclaro));
                RB_amarelo.setChecked(true);
                break;
            default:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorpreto));
                LL_container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorpretoclaro));
                break;
        }


    }
}
