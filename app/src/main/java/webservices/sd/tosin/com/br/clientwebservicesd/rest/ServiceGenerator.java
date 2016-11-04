package webservices.sd.tosin.com.br.clientwebservicesd.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tosin on 03/11/16.
 */

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://%s:8080/";
    public static String host = "192.168.0.121";


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(String.format(API_BASE_URL, host))
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, String host) {
        ServiceGenerator.host = host;
        Retrofit retrofit = builder
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
