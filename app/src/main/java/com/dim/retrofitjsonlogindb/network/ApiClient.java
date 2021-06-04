package com.dim.retrofitjsonlogindb.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*import retrofit2.Retrofit;
*/
public class ApiClient {
    private static String url = "https://api.larntech.net/";

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UsuarioServicio getUsuarioServicio(){
        UsuarioServicio uServicio = getRetrofit().create(UsuarioServicio.class);
        return  uServicio;
    }
}
