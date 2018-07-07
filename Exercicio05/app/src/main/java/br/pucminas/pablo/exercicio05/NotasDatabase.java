package br.pucminas.pablo.exercicio05;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import br.pucminas.pablo.exercicio05.Interface.NotaDAO;
import br.pucminas.pablo.exercicio05.Model.Nota;

@Database(entities = {Nota.class}, version = 1)
public abstract class NotasDatabase extends RoomDatabase {

    private static NotasDatabase INSTANCE;

    public abstract NotaDAO notaDAO();

    public static NotasDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotasDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotasDatabase.class, "notas.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;

    }

}
