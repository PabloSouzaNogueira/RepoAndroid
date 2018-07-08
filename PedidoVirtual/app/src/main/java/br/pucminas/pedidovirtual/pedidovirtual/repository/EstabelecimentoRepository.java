package br.pucminas.pedidovirtual.pedidovirtual.repository;

import br.pucminas.pedidovirtual.pedidovirtual.model.RequestEstabelecimento;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EstabelecimentoRepository {

    @GET("api/v1/pedidovirtual/consultar_qrcode")
    Call<RequestEstabelecimento> consultarQRCode(@Query("qrcode") int qrcode);
}
