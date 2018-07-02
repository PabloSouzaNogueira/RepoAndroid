package br.pucminas.pablo.exercicio01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void CalcularIMC(View view) {

        EditText ET_peso = findViewById(R.id.ET_peso);
        EditText ET_altura = findViewById(R.id.ET_altura);
        TextView TV_resultado = findViewById(R.id.TV_resultado);

        if (ValidaCampo(ET_peso.getText().toString()) && ValidaCampo(ET_altura.getText().toString())) {
            double peso = Double.valueOf(ET_peso.getText().toString());
            double altura = Double.parseDouble(ET_altura.getText().toString()) / 100;
            double imc = Math.round(peso / (altura * altura));
            TV_resultado.setText(String.valueOf(imc));
            StatusIMC(imc);
        } else {
            Toast.makeText(this, "Preencha os valores!", Toast.LENGTH_LONG).show();
        }


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private Boolean ValidaCampo(String campo) {
        return campo != null && !campo.equals("");
    }

    private void StatusIMC(double IMC) {
        TextView TV_status = findViewById(R.id.TV_status);

        if (IMC < 16) {
            TV_status.setText(R.string.status_1);
        } else if (IMC < 17) {
            TV_status.setText(R.string.status_2);
        } else if (IMC < 18.5) {
            TV_status.setText(R.string.status_3);
        } else if (IMC < 25) {
            TV_status.setText(R.string.status_4);
        } else if (IMC < 30) {
            TV_status.setText(R.string.status_5);
        } else if (IMC < 35) {
            TV_status.setText(R.string.status_6);
        } else if (IMC < 40) {
            TV_status.setText(R.string.status_7);
        } else {
            TV_status.setText(R.string.status_8);
        }
    }


}
