package com.example.popularmovies.MovieActivites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.Model.movie_search
import com.example.popularmovies.Model.moviecastresopnse
import com.example.popularmovies.Model.movieresponse
import com.example.popularmovies.Model.videoresponse
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.R
import com.example.popularmovies.movieadapters.movieadapter
import com.example.popularmovies.movieadapters.moviecastdapter
import com.example.popularmovies.movieadapters.videoadapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class activity_second : AppCompatActivity() {


    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val baseURL = "https://image.tmdb.org/t/p/w780/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)


        val id = intent.getStringExtra("id").toInt()

        val type = intent.getStringExtra("type")

        val service=retrofit.create(popinterface::class.java)
        service.getmovies(id,api_key).enqueue(object : Callback<movie_search> {
            override fun onFailure(call: Call<movie_search>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movie_search>, response: Response<movie_search>) {

                val data=response.body()



                if (data != null) {
                    Picasso.get().load(baseURL+data.backdrop_path).resize(413,200).into(iview)
                  Picasso.get().load(baseURL+data.poster_path).into(imageview2)
                }
                tv7.text=data?.original_title
                tv1.text="Release Date    " +data?.release_date
                tv3.text=data?.vote_average+"/10"
                tvoverview.text=data?.overview






            }
        })

        service.getcast(id,api_key).enqueue(object : Callback<moviecastresopnse> {
            override fun onFailure(call: Call<moviecastresopnse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<moviecastresopnse>, response: Response<moviecastresopnse>) {

                val data=response.body()
                val data1= data?.cast


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView20.layoutManager =
                    LinearLayoutManager(this@activity_second, RecyclerView.HORIZONTAL,false)
                rView20.adapter = data1?.let {
                    moviecastdapter(
                        this@activity_second,
                        it,
                        false
                    )
                }



            }
        })

        service.getsimilar(id,api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }




            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView21.layoutManager =
                    LinearLayoutManager(this@activity_second, RecyclerView.HORIZONTAL,false)
                rView21.adapter = data1?.let {
                    movieadapter(
                        this@activity_second,
                        it,
                        false
                    )
                }



            }
        })


        service.getvideos(id,api_key).enqueue(object : Callback<videoresponse> {
            override fun onFailure(call: Call<videoresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }




            override fun onResponse(call: Call<videoresponse>, response: Response<videoresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView22.layoutManager =
                    LinearLayoutManager(this@activity_second, RecyclerView.HORIZONTAL,false)
                rView22.adapter = data1?.let {
                    videoadapter(
                        this@activity_second,
                        it,
                        false
                    )
                }



            }
        })


    }


}
