package com.task.data.network_request

import com.task.data.model.MovieDataModel
import retrofit2.http.GET
/*
* This is ApiService class here we write all network call request*/
interface ApiService {
    @GET("movies")
    fun getAllMovieList(): retrofit2.Call<MovieDataModel>
}