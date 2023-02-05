package com.task.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.task.data.model.MovieDataModel
import com.task.data.repogistory.MovieRepogistory

class MainActivityViewModel (private var movieRepogistory: MovieRepogistory): ViewModel() {
    init {
        movieRepogistory.callMovieApi()
    }
    val moviewDataModel: LiveData<List<MovieDataModel.Movy>> get() = movieRepogistory.movieLiveData
}