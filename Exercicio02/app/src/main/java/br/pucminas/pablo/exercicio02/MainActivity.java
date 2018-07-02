package br.pucminas.pablo.exercicio02;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ET_valorConta)
    TextInputEditText ET_valorConta;
    @BindView(R.id.ET_numeroPessoas)
    TextInputEditText ET_numeroPessoas;
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
    @BindView(R.id.BT_calcular)
    Button BT_calcular;
    @BindView(R.id.TV_valorGarcom)
    TextView TV_valorGarcom;
    @BindView(R.id.TV_valorTotal)
    TextView TV_valorTotal;
    @BindView(R.id.TV_valorIndividual)
    TextView TV_valorIndividual;
    @BindView(R.id.TV_valorGarcomBebem)
    TextView TV_valorGarcomBebem;
    @BindView(R.id.TV_valorTotalBebem)
    TextView TV_valorTotalBebem;
    @BindView(R.id.TV_valorIndividualBebem)
    TextView TV_valorIndividualBebem;

    @BindView(R.id.SB_porcentagemGarcom)
    SeekBar SB_porcentagemGarcom;

    @BindView(R.id.ET_valorContaBebe)
    TextInputEditText ET_valorContaBebe;
    @BindView(R.id.ET_numeroPessoasBebe)
    TextInputEditText ET_numeroPessoasBebe;


    int progressStatus = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SB_porcentagemGarcom.setProgress(progressStatus);
        changeColorLabelSeekbar(progressStatus);
        SB_porcentagemGarcom.setOnSeekBarChangeListener(seekbarListener());
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

    private Boolean validarCampo(String campo) {
        return (campo != null && !campo.equals(""));
    }


    private void changeColorLabelSeekbar(int progressStatus) {
        switch (progressStatus) {
            case 0:
                TV_porcentagem00.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem10.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 10:
                TV_porcentagem10.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem20.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 20:
                TV_porcentagem20.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem30.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 30:
                TV_porcentagem30.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem40.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 40:
                TV_porcentagem40.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem50.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 50:
                TV_porcentagem50.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem60.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 60:
                TV_porcentagem60.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem70.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 70:
                TV_porcentagem70.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem80.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 80:
                TV_porcentagem80.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem90.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 90:
                TV_porcentagem90.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                TV_porcentagem100.setTextColor(ContextCompat.getColor(this, R.color.colorblack));
                break;
            case 100:
                TV_porcentagem100.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                break;
        }
    }


    @OnClick(R.id.BT_calcular)
    void CalcularConta() {
        if (validarCampo(ET_valorConta.getText().toString()) && validarCampo(ET_numeroPessoas.getText().toString())
                && validarCampo(ET_valorContaBebe.getText().toString()) && validarCampo(ET_numeroPessoasBebe.getText().toString())) {

            Locale brasil = new Locale("pt", "BR");


            double Valor = Double.parseDouble(ET_valorConta.getText().toString());
            int Pessoas = Integer.parseInt(ET_numeroPessoas.getText().toString());

            double ValorGarcom = (Valor / 100.0) * progressStatus;
            double ValorTotal = (Valor + ValorGarcom);
            double ValorIndividual = ValorTotal / Pessoas;

            TV_valorGarcom.setText(String.format(brasil, "R$ %02.2f", ValorGarcom));
            TV_valorTotal.setText(String.format(brasil, "R$ %02.2f", ValorTotal));
            TV_valorIndividual.setText(String.format(brasil, "R$ %02.2f", ValorIndividual));


            double ValorBebida = Double.parseDouble(ET_valorContaBebe.getText().toString());
            int PessoasBebida = Integer.parseInt(ET_numeroPessoasBebe.getText().toString());

            double ValorGarcomBebem = (ValorBebida / 100.0) * progressStatus;
            double ValorTotalBebem = (ValorBebida + ValorGarcomBebem);
            double ValorIndividualBebem = ValorTotalBebem / PessoasBebida;

            TV_valorGarcomBebem.setText(String.format(brasil, "R$ %02.2f", ValorGarcomBebem));
            TV_valorTotalBebem.setText(String.format(brasil, "R$ %02.2f", ValorTotalBebem));
            TV_valorIndividualBebem.setText(String.format(brasil, "R$ %02.2f", ValorIndividualBebem));
        } else {
            Toast.makeText(this, "Preencha os valores!", Toast.LENGTH_LONG).show();
        }

        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
