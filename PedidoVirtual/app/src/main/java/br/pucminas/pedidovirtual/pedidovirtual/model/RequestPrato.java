package br.pucminas.pedidovirtual.pedidovirtual.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPrato {

    @Expose
    @SerializedName("ID_Prato")
    private int idPrato;
    @Expose
    @SerializedName("Nome")
    private String nome;
    @Expose
    @SerializedName("Descricao")
    private String descricao;
    @Expose
    @SerializedName("Foto")
    private String foto;


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
}
