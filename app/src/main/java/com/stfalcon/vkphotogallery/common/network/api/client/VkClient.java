package com.stfalcon.vkphotogallery.common.network.api.client;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by troy379 on 16.12.16.
 */
public class VkClient implements Interceptor {

    private Retrofit retrofit;
    private Object service;

    public static VkClient instanse = new VkClient();

    public static VkClient getInstanse() {
        return instanse;
    }

    private VkClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(this)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl.Builder urlBuilder = originalUrl.newBuilder();
        urlBuilder.addQueryParameter("v", Config.VERSION);

        Request.Builder requestBuilder = original.newBuilder()
                .url(urlBuilder.build());

        Request request = requestBuilder.build();

        return chain.proceed(request);
    }

    @SuppressWarnings("unchecked")
    public static <T> T makeService(Class<T> serviceClass) {
        VkClient client = getInstanse();
        if (client.service == null || !serviceClass.isInstance(client.service)) {
            client.service = client.retrofit.create(serviceClass);
        }

        return (T) client.service;
    }
}