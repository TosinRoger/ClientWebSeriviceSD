package webservices.sd.tosin.com.br.clientwebservicesd.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tosin on 03/11/16.
 *
 * Utilizado pelo retrofit para criar a conexao com o servidor,
 * estabelecer como retrofit ira fazer o parse das informacoes recebidas
 * e o formato das informacoes recebidas, este caso JSON
 */

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://192.168.109.78:8080/";


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, String host) {
        Retrofit retrofit = builder
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
