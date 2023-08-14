package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent
import com.example.weatherapp.presentation.WeatherFragment

class MainActivity : AppCompatActivity() {

    lateinit var component: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerAppComponent.factory().create(applicationContext)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WeatherFragment())
            .commit()

//        val key = BuildConfig.API_KEY
//        Log.d("key", key)
    }
}