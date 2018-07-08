package br.pucminas.pedidovirtual.pedidovirtual.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


@RealmClass
public class Mesa implements RealmModel {

    @PrimaryKey
    @SerializedName("idQRCode")
    private int idQRCode;
    @SerializedName("mesa")
    private int mesa;
    @SerializedName("idEstabelecimento")
    private int idEstabelecimento;


    public int getIdQRCode() {
        return idQRCode;
    }

    public void setIdQRCode(int idQRCode) {
        this.idQRCode = idQRCode;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

}
