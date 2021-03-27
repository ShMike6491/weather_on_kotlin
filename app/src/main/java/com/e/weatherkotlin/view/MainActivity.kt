package com.e.weatherkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.weatherkotlin.R
import com.e.weatherkotlin.view.details.DetailsFragment
import com.e.weatherkotlin.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
