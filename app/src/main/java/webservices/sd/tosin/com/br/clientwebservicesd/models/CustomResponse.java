package webservices.sd.tosin.com.br.clientwebservicesd.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tosin on 03/11/16.
 */

public class CustomResponse {

    @SerializedName("status")
    String status;
    @SerializedName("msg")
    public String msg;
}
