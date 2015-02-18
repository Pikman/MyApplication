package com.example.pik92_000.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by pik92_000 on 18.02.2015.
 */
public class MyApp extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }
}
