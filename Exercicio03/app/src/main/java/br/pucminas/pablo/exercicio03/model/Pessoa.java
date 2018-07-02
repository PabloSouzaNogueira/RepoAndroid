package br.pucminas.pablo.exercicio03.model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private String nome;
    private byte[] foto;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
