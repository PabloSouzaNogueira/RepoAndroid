package br.pucminas.pablo.exercicio06.model;

import android.util.Base64;

import java.io.Serializable;

public class Filme implements Serializable {

    public Filme() {

    }

    public Filme(String nome) {
        this.nome = nome;
        this.votos = 0;
    }

    private String nome;
    private int votos;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getId() {
        return Base64.encodeToString(nome.toLowerCase().getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

}
