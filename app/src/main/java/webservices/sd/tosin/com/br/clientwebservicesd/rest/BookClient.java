package webservices.sd.tosin.com.br.clientwebservicesd.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import webservices.sd.tosin.com.br.clientwebservicesd.models.Book;

/**
 * Created by tosin on 03/11/16.
 *
 * Interface utilizada pelo retrofit para encontrar os ENDPOINTs no servidor
 */
public interface BookClient {
    @GET("/webservicessd/rest/books")
    Call<List<Book>> getBooks();
    @GET("/webservicessd/rest/books/{id}")
    Call<List<Book>> getBooks(@Path("id") long id);
    @GET("/webservicessd/rest/books/name/{title}")
    Call<List<Book>> getBooks(@Path("title") String title);
    @GET("/webservicessd/rest/books/mybooks")
    Call<List<Book>> getMyBooks(@Header("Authorization") String authorization);
}
