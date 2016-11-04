package webservices.sd.tosin.com.br.clientwebservicesd.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import webservices.sd.tosin.com.br.clientwebservicesd.models.CustomResponse;
import webservices.sd.tosin.com.br.clientwebservicesd.models.User;

/**
 * Created by tosin on 03/11/16.
 */

public interface UserClient {
    @POST("/webservicessd/rest/user")
    Call<CustomResponse> createOrUpdate(@Body  User user);

}
