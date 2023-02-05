package com.task.data.repogistory

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.model.MovieDataModel
import com.task.data.network_request.RetrofitClient
import com.task.utils.Commons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/*Repogistory to communicate with network call or local db as required*/
class MovieRepogistory (private val context: Context) {
    private var mutableMovieLiveData = MutableLiveData<List<MovieDataModel.Movy>>()
    val movieLiveData: LiveData<List<MovieDataModel.Movy>> get() = mutableMovieLiveData

    fun callMovieApi(){
        val call = RetrofitClient.getApiServiceInstant().getAllMovieList()
        call.enqueue(object : Callback<MovieDataModel>{
            override fun onResponse(
                call: Call<MovieDataModel>,
                response: Response<MovieDataModel>
            ) {
                if (response.isSuccessful){
                    if (response.body()!=null){
                        mutableMovieLiveData.postValue(response.body()?.movies)
                    }else{
                        mutableMovieLiveData.postValue(emptyList())
                    }
                }else{
                    mutableMovieLiveData.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<MovieDataModel>, t: Throwable) {
                Log.d("MovieRepogistory", "onFailure: ${t.message}")
                Commons.showToast(context,"${t.message}")
                mutableMovieLiveData.postValue(emptyList())
            }

        })
    }
}