package br.pucminas.pablo.exercicio03;

import java.util.ArrayList;
import java.util.List;

import br.pucminas.pablo.exercicio03.model.Consumo;
import br.pucminas.pablo.exercicio03.model.Pessoa;

public class Persistencia {

    private static List<Consumo> consumos = null;
    private static List<Pessoa> pessoas = null;

    public Persistencia() {

        if (consumos == null) {
            consumos = new ArrayList<>();
        }

        if (pessoas == null) {
            pessoas = new ArrayList<>();
        }
    }


    public static List<Consumo> getConsumos() {
        return consumos;
    }

    public static List<Pessoa> getPessoas() {
        return pessoas;
    }
}
