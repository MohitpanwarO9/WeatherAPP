package com.example.weather

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

        val Cloud1=AnimationUtils.loadAnimation(this,R.anim.cloud1)
        val Cloud2=AnimationUtils.loadAnimation(this,R.anim.cloud2)


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

        val formatted:String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH")
                formatted = current.format(formatter)
        } else {
            var current = Date()
            val formatter = SimpleDateFormat("HH")
                formatted = formatter.format(current)

        }

        if(formatted.toInt()>=20 || formatted.toInt()<6){
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


            cloud1.startAnimation(Cloud1)
            cloud2.startAnimation(Cloud2)

        }

        // get weather button and intent //
        AtcityName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Do what you want here
                val cityname:String=AtcityName.text.toString()

                val intent:Intent=Intent(applicationContext,Report::class.java)
                intent.putExtra("city",cityname)

                startActivity(intent)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        searchBT.setOnClickListener {

            val cityname:String=AtcityName.text.toString()

            val intent:Intent=Intent(applicationContext,Report::class.java)
            intent.putExtra("city",cityname)

            startActivity(intent)
        }

    }



}