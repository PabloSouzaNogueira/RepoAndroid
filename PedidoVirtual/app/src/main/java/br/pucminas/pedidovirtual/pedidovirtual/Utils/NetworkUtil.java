package br.pucminas.pedidovirtual.pedidovirtual.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo) {
                return (networkInfo.getType() == ConnectivityManager.TYPE_WIFI && networkInfo.isConnected())
                        || (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE && networkInfo.isConnected());
            }
        }
        return false;
    }
}
