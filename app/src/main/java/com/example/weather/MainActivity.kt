package com.example.weather

import android.content.Intent
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi

import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Animation //

        val animSlideup=AnimationUtils.loadAnimation(this,R.anim.slideup)
        heading.startAnimation(animSlideup)

        val animSlidesun=AnimationUtils.loadAnimation(this,R.anim.slidesun)
        imageView.startAnimation(animSlidesun)

        val animSlidecity=AnimationUtils.loadAnimation(this,R.anim.slidecity)
        AtcityName.startAnimation(animSlidecity)

        val animSlidebut=AnimationUtils.loadAnimation(this,R.anim.slidbut)
        searchBT.startAnimation(animSlidebut)

        val star1=AnimationUtils.loadAnimation(this,R.anim.fade1)
        val star2=AnimationUtils.loadAnimation(this,R.anim.fade2)
        val star3=AnimationUtils.loadAnimation(this,R.anim.fade3)

        // AutoComplete //

        val autocomp=findViewById<AutoCompleteTextView>(R.id.AtcityName)
        val city=resources.getStringArray(R.array.citys)

        val adpater=ArrayAdapter(this,
            android.R.layout.simple_list_item_1,city)
            autocomp.threshold=0
            autocomp.setAdapter(adpater)

        //time comparision code//

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        val formatted = current.format(formatter)

        if(formatted.toInt()>=20 || formatted.toInt()<=6){
            imageView.setImageResource(R.drawable.moon)

            //starting animation
            smallstar.startAnimation(star1)
            smallstar2.startAnimation(star3)
            bigstar.startAnimation(star2)
        }
        else
        {
            smallstar.visibility=View.GONE
            smallstar2.visibility=View.GONE
            bigstar.visibility=View.GONE
        }

        // get weather button and intent //

        searchBT.setOnClickListener {

            val cityname:String=AtcityName.text.toString()

            val intent:Intent=Intent(applicationContext,Report::class.java)
            intent.putExtra("city",cityname)

            startActivity(intent)
        }

    }



}