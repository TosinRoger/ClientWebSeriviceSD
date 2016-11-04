package webservices.sd.tosin.com.br.clientwebservicesd.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tosin on 03/11/16.
 */

public class Book implements Serializable {

    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("about")
    String author;
    @SerializedName("about")
    String about;
    @SerializedName("timeDevolution")
    long timeDevolution;
}
