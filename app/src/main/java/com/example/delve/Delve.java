package com.example.delve;

import android.app.Application;

import com.example.delve.service.controllers.ApplicationController;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class Delve extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ApplicationController.getInstance().init();
    }
}
