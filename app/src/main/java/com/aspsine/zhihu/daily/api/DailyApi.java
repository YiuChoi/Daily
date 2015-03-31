package com.aspsine.zhihu.daily.api;

import android.content.Context;

import com.aspsine.zhihu.daily.BuildConfig;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Aspsine on 2015/3/30.
 */
public class DailyApi {

    private static final String API = "http://news.at.zhihu.com/api/4";

    private static RestAdapter restAdapter;

    public static DailyService createApi(Context context) {
        if (restAdapter == null) {
            synchronized (DailyApi.class) {
                if (restAdapter == null) {
                    restAdapter = new RestAdapter.Builder()
                            .setEndpoint(API)
                            .setClient(new OkClient())
                            .setConverter(new GsonConverter(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                            .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                            .build();
                }
            }
        }
        return restAdapter.create(DailyService.class);
    }

}