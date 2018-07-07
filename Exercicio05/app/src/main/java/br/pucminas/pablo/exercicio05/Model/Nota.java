package br.pucminas.pablo.exercicio05.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "notas")
public class Nota implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "texto")
    private String texto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public String getTexto() {
        return texto;
    }

    public void setTexto(@NonNull String texto) {
        this.texto = texto;
    }
}
