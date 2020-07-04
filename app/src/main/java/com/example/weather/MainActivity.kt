package com.example.weather

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


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

        // AutoComplete //

        val autocomp=findViewById<AutoCompleteTextView>(R.id.AtcityName)
        val city=resources.getStringArray(R.array.citys)

        val adpater=ArrayAdapter(this,
            android.R.layout.simple_list_item_1,city)
            autocomp.threshold=0
            autocomp.setAdapter(adpater)


        searchBT.setOnClickListener {

            val cityname:String=AtcityName.text.toString()

            val intent:Intent=Intent(applicationContext,Report::class.java)
            intent.putExtra("city",cityname)

            startActivity(intent)
        }

    }



}