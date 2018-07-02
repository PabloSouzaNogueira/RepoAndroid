package br.pucminas.pablo.exercicio03.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import br.pucminas.pablo.exercicio03.Persistencia;
import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.model.Consumo;
import br.pucminas.pablo.exercicio03.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaxaServicoActivity extends AppCompatActivity {

    @BindView(R.id.TV_porcentagem00)
    TextView TV_porcentagem00;
    @BindView(R.id.TV_porcentagem10)
    TextView TV_porcentagem10;
    @BindView(R.id.TV_porcentagem20)
    TextView TV_porcentagem20;
    @BindView(R.id.TV_porcentagem30)
    TextView TV_porcentagem30;
    @BindView(R.id.TV_porcentagem40)
    TextView TV_porcentagem40;
    @BindView(R.id.TV_porcentagem50)
    TextView TV_porcentagem50;
    @BindView(R.id.TV_porcentagem60)
    TextView TV_porcentagem60;
    @BindView(R.id.TV_porcentagem70)
    TextView TV_porcentagem70;
    @BindView(R.id.TV_porcentagem80)
    TextView TV_porcentagem80;
    @BindView(R.id.TV_porcentagem90)
    TextView TV_porcentagem90;
    @BindView(R.id.TV_porcentagem100)
    TextView TV_porcentagem100;

    @BindView(R.id.TV_pessoaTotal)
    TextView TV_pessoaTotal;
    @BindView(R.id.TV_consumoTotal)
    TextView TV_consumoTotal;
    @BindView(R.id.TV_valorGarcom)
    TextView TV_valorGarcom;
    @BindView(R.id.TV_valorTotal)
    TextView TV_valorTotal;
    @BindView(R.id.TV_valorIndividual)
    TextView TV_valorIndividual;


    @BindView(R.id.SB_porcentagemGarcom)
    SeekBar SB_porcentagemGarcom;

    int progressStatus = 10;
    double consumoTotal = 0;
    int pessoaTotal = 0;

    Locale brasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxa_servico);

        ButterKnife.bind(this);

        brasil = new Locale("pt", "BR");

        if (verifyPersistencia()) {
            Toast.makeText(this, "Favor preencher as telas de consumo e de pessoas primeiro.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            SB_porcentagemGarcom.setProgress(progressStatus);
            changeColorLabelSeekbar(progressStatus);
            SB_porcentagemGarcom.setOnSeekBarChangeListener(seekbarListener());

            pessoaTotal = Persistencia.getPessoas().size();
            for (Consumo C : Persistencia.getConsumos()) {
                consumoTotal += C.getValorTotal();
            }

            TV_pessoaTotal.setText(String.valueOf(pessoaTotal));
            TV_consumoTotal.setText(String.format(brasil, "R$ %02.2f", consumoTotal));

            calcularResultado();
        }
    }

    private boolean verifyPersistencia() {
        return Persistencia.getPessoas() == null || Persistencia.getPessoas().size() == 0 || Persistencia.getConsumos() == null || Persistencia.getConsumos().size() == 0;
    }

    private void calcularResultado() {
        double ValorGarcom = (consumoTotal / 100.0) * progressStatus;
        double ValorTotal = (consumoTotal + ValorGarcom);
        double ValorIndividual = ValorTotal / pessoaTotal;

        TV_valorGarcom.setText(String.format(brasil, "R$ %02.2f", ValorGarcom));
        TV_valorTotal.setText(String.format(brasil, "R$ %02.2f", ValorTotal));
        TV_valorIndividual.setText(String.format(brasil, "R$ %02.2f", ValorIndividual));
    }


    private SeekBar.OnSeekBarChangeListener seekbarListener() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (progress > progressStatus) {
                        progressStatus += 10;
                        changeColorLabelSeekbar(progressStatus);

                    } else if (progress < progressStatus) {
                        progressStatus -= 10;
                        changeColorLabelSeekbar(progressStatus);
                    }

                    SB_porcentagemGarcom.setProgress(progressStatus);

                    calcularResultado();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }


    private void changeColorLabelSeekbar(int progressStatus) {
        switch (progressStatus) {
            case 0:
                TV_porcentagem00.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem10.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 10:
                TV_porcentagem10.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem20.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 20:
                TV_porcentagem20.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem30.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 30:
                TV_porcentagem30.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem40.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 40:
                TV_porcentagem40.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem50.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 50:
                TV_porcentagem50.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem60.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 60:
                TV_porcentagem60.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem70.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 70:
                TV_porcentagem70.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem80.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 80:
                TV_porcentagem80.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem90.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 90:
                TV_porcentagem90.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                TV_porcentagem100.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 100:
                TV_porcentagem100.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                break;
        }
    }
}
