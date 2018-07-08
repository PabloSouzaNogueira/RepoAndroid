package br.pucminas.pedidovirtual.pedidovirtual.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import br.pucminas.pedidovirtual.pedidovirtual.R;

public class PermissionUtil {
    private final static int REQUEST_PERMISSIONS_CODE = 128;

    public static boolean verifyPermission(String[] permissoes, Context context) {
        try {
            boolean isAllAccepted = true;
            for (String permissao : permissoes) {
                if (ContextCompat.checkSelfPermission(context, permissao) != PackageManager.PERMISSION_GRANTED) {
                    isAllAccepted = false;
                    break;
                }
            }

            return isAllAccepted;
        } catch (Exception ex) {
            Log.e("ERROR", "PermissionUtil - verifyPermission");
        }
        return false;
    }


    public static void resultRequest(int requestCode, String permissions[], int[] grantResults, Activity activity) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS_CODE: {
                for (int I = 0; I < grantResults.length; I++) {
                    if (permissions[I].equalsIgnoreCase(Manifest.permission.CAMERA) && grantResults[I] != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[I])) {
                            Toast.makeText(activity, R.string.habilitar_permissaoCamera, Toast.LENGTH_SHORT).show();
                        }
                        return;

                    } else if ((permissions[I].equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE) || permissions[I].equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) && grantResults[I] != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[I])) {
                            Toast.makeText(activity, R.string.habilitar_permissaoArmazenamento, Toast.LENGTH_SHORT).show();
                        }
                        return;

                    }  else if (permissions[I].equalsIgnoreCase(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[I] != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[I])) {
                            Toast.makeText(activity, R.string.habilitar_permissaoLocalizacao, Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                }
            }
        }
    }
}
