package br.pucminas.pedidovirtual.pedidovirtual.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class Prato implements RealmModel {


    @SerializedName("idPrato")
    @Expose
    private int idPrato;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("preco")
    @Expose
    private double preco;



    public int getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
