package com.task.ui.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.R
import com.task.data.repogistory.MovieRepogistory
import com.task.databinding.ActivityMainBinding
import com.task.ui.view.adapters.MovieAdapters
import com.task.ui.view_model.MainActivityViewModel
import com.task.ui.view_model_factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var mainActivityViewModel: MainActivityViewModel? = null
    private lateinit var movieRepogistory: MovieRepogistory
    private lateinit var movieAdapters: MovieAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Data binding to update view on screen */
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        movieRepogistory = MovieRepogistory(this)
        mainActivityViewModel = ViewModelProvider(this,MainViewModelFactory(movieRepogistory))[MainActivityViewModel::class.java]
        binding?.viewModel = mainActivityViewModel
        initView()
    }

    private fun initView() {
        binding?.progressBar?.visibility = View.VISIBLE
        movieAdapters = MovieAdapters()
        binding?.movieRecyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.movieRecyclerView?.adapter = movieAdapters

        obseveMovieData()
    }

    private fun obseveMovieData() {
        /*Observe movie data at network call*/
        mainActivityViewModel?.moviewDataModel?.observe(this, Observer {
            Log.d(TAG, "initView: ")
            binding?.progressBar?.visibility = View.GONE
            movieAdapters.setData(it)
        })
    }
}