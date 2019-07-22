package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.Model.Searchresonse
import com.example.popularmovies.Network.popinterface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service=retrofit.create(popinterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val type = intent.getStringExtra("text")


        service.getSearch(api_key,type).enqueue(object : Callback<Searchresonse> {
            override fun onFailure(call: Call<Searchresonse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<Searchresonse>, response: Response<Searchresonse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewnew.layoutManager =
                    GridLayoutManager(this@SearchActivity,2, RecyclerView.VERTICAL,false)
                rViewnew.adapter = data1?.let {
                    SearchAdapter(
                        this@SearchActivity,
                        it,
                        false
                    )
                }



            }
        })
    }
}
