package com.amirely.elite.bakit.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

//    private static String BASE_URL = "https://api.themoviedb.org/3/";
//    private static String API_KEY = "71ab1b19293efe581c569c1c79d0f004";

    private static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
//    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
//                .addQueryParameter("api_key", API_KEY)
                .build();
        request = request.newBuilder().url(url)
                .build();

        return chain.proceed(request);
    })
            .build();
//            .addInterceptor(httpLoggingInterceptor).build();

    private static Gson gsonBuilder = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build();

    public static <T> T create(Class<T> serviceClass) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                    .build();
        }
        return retrofit.create(serviceClass);
    }
}
