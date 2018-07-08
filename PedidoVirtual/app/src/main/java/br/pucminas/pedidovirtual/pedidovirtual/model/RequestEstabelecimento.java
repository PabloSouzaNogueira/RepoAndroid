package br.pucminas.pedidovirtual.pedidovirtual.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestEstabelecimento extends BaseModel {


    @Expose
    @SerializedName("ID_Estabelecimento")
    private int idEstabelecimento;
    @Expose
    @SerializedName("Nome")
    private String nome;
    @Expose
    @SerializedName("Latitude")
    private Double latitude;
    @Expose
    @SerializedName("Longitude")
    private Double longitude;
    @Expose
    @SerializedName("Rua")
    private String rua;
    @Expose
    @SerializedName("Numero")
    private String numero;
    @Expose
    @SerializedName("Bairro")
    private String bairro;
    @Expose
    @SerializedName("Cidade")
    private String cidade;
    @Expose
    @SerializedName("Estado")
    private String estado;
    @Expose
    @SerializedName("CEP")
    private String cep;
    @Expose
    @SerializedName("Foto")
    private String foto;
    @Expose
    @SerializedName("Pratos")
    private List<RequestPrato> requestPratos;
    @Expose
    @SerializedName("Mesa")
    private int mesa;

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<RequestPrato> getRequestPratos() {
        return requestPratos;
    }

    public void setRequestPratos(List<RequestPrato> requestPratos) {
        this.requestPratos = requestPratos;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
}
