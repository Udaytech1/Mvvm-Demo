package com.task.ui.view_model_factory

import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.data.repogistory.MovieRepogistory
import com.task.ui.view_model.MainActivityViewModel

class MainViewModelFactory(private var movieRepogistory: MovieRepogistory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
           return MainActivityViewModel(this.movieRepogistory) as T
        }else{
           throw IllegalArgumentException("View model not found")
        }
    }
}