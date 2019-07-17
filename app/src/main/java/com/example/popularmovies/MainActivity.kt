package com.example.popularmovies


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val api_key:String="API-KEY"
    var maxLimit : Int =996
    val retrofit=Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        start()





    }
    fun start()
    {
        val service=retrofit.create(popinterface::class.java)



            service.getPopular(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView.adapter = data1?.let { movieadapter(this@MainActivity, it,false) }



            }
        })


        service.getToprated(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView2.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView2.adapter = data1?.let { movieadapter(this@MainActivity, it,false) }



            }
        })


        service.getUpcoming(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView3.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView3.adapter = data1?.let { movieadapter(this@MainActivity, it,false) }



            }
        })


        service.getNowplaying(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView0.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView0.adapter = data1?.let { upmovieadapter(this@MainActivity, it,false) }



            }
        })
    }
}
