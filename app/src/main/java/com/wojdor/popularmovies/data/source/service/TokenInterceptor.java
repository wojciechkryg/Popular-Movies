package com.wojdor.popularmovies.data.source.service;

import android.support.annotation.NonNull;

import com.wojdor.popularmovies.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class TokenInterceptor implements Interceptor {

    private static final String API_KEY = "api_key";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.TMDB_API_TOKEN).build();
        Request.Builder requestBuilder = original.newBuilder().url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
