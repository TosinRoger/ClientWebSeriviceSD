package webservices.sd.tosin.com.br.clientwebservicesd.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import webservices.sd.tosin.com.br.clientwebservicesd.models.CustomResponse;

/**
 * Created by tosin on 05/11/16.
 *
 * Interface utilizada pelo retrofit para encontrar os ENDPOINTs no servidor
 */
public interface LoanClient {
    @POST("/webservicessd/rest/loan")
    Call<CustomResponse> loan(@Header("Authorization") String authorization, @Body Object id);
    @POST("/webservicessd/rest/loan/renovation")
    Call<CustomResponse> renovation(@Header("Authorization") String authorization, @Body Object id);
    @POST("/webservicessd/rest/loan/devolution")
    Call<CustomResponse> devolution(@Header("Authorization") String authorization, @Body Object id);
    @POST("/webservicessd/rest/loan/reservation")
    Call<CustomResponse> reservation(@Header("Authorization") String authorization, @Body Object id);
}
