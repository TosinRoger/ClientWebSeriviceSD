package webservices.sd.tosin.com.br.clientwebservicesd.models;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tosin on 03/11/16.
 */

public class User implements Serializable {

    @SerializedName("username")
    public String username;
    @SerializedName("password")
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String base64() {
        String temp = this.username + ":" + this.password;

        byte[] encode = Base64.encode(temp.getBytes(), Base64.DEFAULT);
        String end = "Basic " + new String(encode);
        return end;
    }
}
