package webservices.sd.tosin.com.br.clientwebservicesd.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tosin on 03/11/16.
 */

public class Book implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("author")
    public String author;
    @SerializedName("about")
    public String about;
    @SerializedName("timeDevolution")
    public long timeDevolution;
    @SerializedName("available")
    public boolean available;
}
