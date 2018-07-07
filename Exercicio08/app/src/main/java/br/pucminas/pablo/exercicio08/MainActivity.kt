package br.pucminas.pablo.exercicio08


import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import java.util.Locale


class MainActivity : AppCompatActivity() {

    var progressStatus = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SB_porcentagemGarcom.setProgress(progressStatus)
        changeColorLabelSeekbar(progressStatus)
        SB_porcentagemGarcom.setOnSeekBarChangeListener(seekbarListener())
    }

    private fun seekbarListener(): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (progress > progressStatus) {
                        progressStatus += 10
                        changeColorLabelSeekbar(progressStatus)

                    } else if (progress < progressStatus) {
                        progressStatus -= 10
                        changeColorLabelSeekbar(progressStatus)
                    }

                    SB_porcentagemGarcom.setProgress(progressStatus)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }
    }


    private fun validarCampo(campo: String?): Boolean {
        return campo != null && campo != ""
    }


    private fun changeColorLabelSeekbar(progressStatus: Int) {
        when (progressStatus) {
            0 -> {
                TV_porcentagem00.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem10.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            10 -> {
                TV_porcentagem10.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem20.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            20 -> {
                TV_porcentagem20.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem30.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            30 -> {
                TV_porcentagem30.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem40.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            40 -> {
                TV_porcentagem40.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem50.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            50 -> {
                TV_porcentagem50.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem60.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            60 -> {
                TV_porcentagem60.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem70.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            70 -> {
                TV_porcentagem70.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem80.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            80 -> {
                TV_porcentagem80.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem90.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            90 -> {
                TV_porcentagem90.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                TV_porcentagem100.setTextColor(ContextCompat.getColor(this, R.color.colorblack))
            }
            100 -> TV_porcentagem100.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    fun CalcularConta(view: View) {
        if (validarCampo(ET_valorConta.text.toString()) && validarCampo(ET_numeroPessoas.text.toString())
                && validarCampo(ET_valorContaBebe.text.toString()) && validarCampo(ET_numeroPessoasBebe.text.toString())) {

            val brasil = Locale("pt", "BR")


            val valor = java.lang.Double.parseDouble(ET_valorConta.text.toString())
            val pessoas = Integer.parseInt(ET_numeroPessoas.text.toString())

            val valorGarcom = valor / 100.0 * progressStatus
            val valorTotal = valor + valorGarcom
            val valorIndividual = valorTotal / pessoas

            TV_valorGarcom.text = String.format(brasil, "R$ %02.2f", valorGarcom)
            TV_valorTotal.text = String.format(brasil, "R$ %02.2f", valorTotal)
            TV_valorIndividual.text = String.format(brasil, "R$ %02.2f", valorIndividual)


            val valorBebida = java.lang.Double.parseDouble(ET_valorContaBebe.text.toString())
            val pessoasBebida = Integer.parseInt(ET_numeroPessoasBebe.text.toString())

            val valorGarcomBebem = valorBebida / 100.0 * progressStatus
            val valorTotalBebem = valorBebida + valorGarcomBebem
            val valorIndividualBebem = valorTotalBebem / pessoasBebida

            TV_valorGarcomBebem.text = String.format(brasil, "R$ %02.2f", valorGarcomBebem)
            TV_valorTotalBebem.text = String.format(brasil, "R$ %02.2f", valorTotalBebem)
            TV_valorIndividualBebem.text = String.format(brasil, "R$ %02.2f", valorIndividualBebem)
        } else {
            Toast.makeText(this, "Preencha os valores!", Toast.LENGTH_LONG).show()
        }

        hideKeyboard()
    }
}
