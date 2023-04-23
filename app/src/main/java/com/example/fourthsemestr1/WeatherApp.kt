package com.example.fourthsemestr1

import android.app.Application
import com.example.fourthsemestr1.di.AppComponent
import com.example.fourthsemestr1.di.DaggerAppComponent



class  WeatherApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
//            .context(context = this)
            .application(this)
            .build()
    }
}