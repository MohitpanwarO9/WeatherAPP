package com.example.weather

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.android.synthetic.main.activity_report.*
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Report : AppCompatActivity() {

     var city:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val bundle:Bundle?=intent.extras
        city=bundle!!.getString("city")

       getWeather().execute()

    }


    inner class getWeather():AsyncTask<String,String,String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            loader.visibility=View.VISIBLE
            mainCont.visibility=View.GONE
            errorText.visibility=View.GONE
        }

        override fun doInBackground(vararg url: String?): String? {
                var res:String?

            try{
                res = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=4f8a336733a0d8a91e77c5aff01c1fae").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                res = null
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys=jsonObj.getJSONObject("sys")
                val weat=jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))

                val tem=main.getString("temp")+"°C"
                val temin="Min Temp "+main.getString("temp_min") +"°C"
                val temax="Max Temp "+main.getString("temp_max")+ "°C"
                val name=jsonObj.getString("name")+","+sys.getString("country")
                val dep=weat.getString("description")


                temp.text=tem
                temp_min.text=temin
                temp_max.text=temax
                cityName.text=name
                state.text=dep
                update.text=updatedAtText

                loader.visibility=View.GONE
                mainCont.visibility=View.VISIBLE


            }catch (e:Exception){
                loader.visibility=View.GONE
                errorText.visibility=View.VISIBLE
            }


        }


    }


    }

