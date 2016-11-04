package webservices.sd.tosin.com.br.clientwebservicesd.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tosin on 03/11/16.
 */

public class User implements Serializable {

    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
