package com.example.popularmovies.Model

data class peopleresponse(
    val page : String,
    var results : ArrayList<people>
)

data class people(
    var id : String,
    var name : String,
    var profile_path : String,
    var popularity:Number

)
