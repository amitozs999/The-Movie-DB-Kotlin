package com.example.popularmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.Model.movieresponse
import com.example.popularmovies.Model.peopleresponse
import com.example.popularmovies.MovieActivites.MainActivity
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.TvActivities.MainActivitytv
import com.example.popularmovies.movieadapters.movieadapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_5.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityPeople : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    lateinit var toolbar: android.app.ActionBar
    var language:String="en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
        var toolbar = supportActionBar
        val naview=findViewById<View>(R.id.nav) as? BottomNavigationView
        var menu = naview?.menu
        var menuItem = menu?.getItem(2)
        menuItem?.isChecked = true
        naview?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    val  intent1= Intent(this, MainActivity::class.java)
                    startActivity(intent1)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.person -> {
                    val  intent3=Intent(this, MainActivityPeople::class.java)
                    startActivity(intent3)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.tv -> {
                    val  intent2=Intent(this, MainActivitytv::class.java)
                    startActivity(intent2)
                    return@setOnNavigationItemSelectedListener  true
                }

                else-> return@setOnNavigationItemSelectedListener  true


            }

        }

        start()
    }
    override fun onBackPressed() {
        val i=Intent(this,MainActivity::class.java)
        startActivity(i)
        finishAffinity()
    }

    fun start()
    {
        val service=retrofit.create(popinterface::class.java)

        service.getPopularpeople(api_key).enqueue(object : Callback<peopleresponse> {
            override fun onFailure(call: Call<peopleresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<peopleresponse>, response: Response<peopleresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewperson.layoutManager =
                    GridLayoutManager(this@MainActivityPeople,2, RecyclerView.VERTICAL,false)
                rViewperson.adapter = data1?.let {
                    popularpeopleadapter(
                        this@MainActivityPeople,
                        it,
                        false
                    )
                }



            }
        })


    }
}
