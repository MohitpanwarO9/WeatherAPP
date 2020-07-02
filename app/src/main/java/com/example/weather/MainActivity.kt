package com.example.weather

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        searchBT.setOnClickListener {

            val cityname:String=etcityName.text.toString()

            val intent:Intent=Intent(applicationContext,Report::class.java)
            intent.putExtra("city",cityname)

            startActivity(intent)
        }

    }



}