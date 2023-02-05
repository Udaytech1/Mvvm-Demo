package com.task.data.model
/*
* This is data class to store movie data*/
data class MovieDataModel(
    val movies: List<Movy>
){
    data class Movy(
        val backdrop: String,
        val cast: Any,
        val classification: String,
        val director: Any,
        val genres: Any,
        val id: String,
        val imdb_rating: Double,
        val length: String,
        val overview: String,
        val poster: String,
        val released_on: String,
        val slug: String,
        val title: String
    )
}