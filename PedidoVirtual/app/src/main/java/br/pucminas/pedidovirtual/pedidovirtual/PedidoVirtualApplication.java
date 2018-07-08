package br.pucminas.pedidovirtual.pedidovirtual;

import android.app.Application;


import io.realm.Realm;

public class PedidoVirtualApplication extends Application {

    private static PedidoVirtualApplication instanceApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        instanceApplication = this;


    }

    public static synchronized PedidoVirtualApplication getInstance() {
        return instanceApplication;
    }

}
