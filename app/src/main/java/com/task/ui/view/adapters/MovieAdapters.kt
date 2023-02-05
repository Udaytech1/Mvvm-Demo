package com.task.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.model.MovieDataModel
import com.task.databinding.ListItemMovieLayoutBinding
import com.task.utils.Commons
/*Movie adapter to listing on screen */
class MovieAdapters (): RecyclerView.Adapter<MovieAdapters.MyViewHolder>() {
    private val moviewList = ArrayList<MovieDataModel.Movy>()
    inner class MyViewHolder(binding: ListItemMovieLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var binding: ListItemMovieLayoutBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemMovieLayoutBinding = DataBindingUtil.inflate<ListItemMovieLayoutBinding>(layoutInflater, R.layout.list_item_movie_layout,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = moviewList[position]
        holder?.binding?.apply {
            movieData = model
            executePendingBindings()
        }
        Commons.setImage(holder.binding.movieImage,model.poster)
    }

    fun setData(newList: List<MovieDataModel.Movy>){
        val callBack = MovieListCallBack(oldList = moviewList, newList = newList)
        val diffResult = DiffUtil.calculateDiff(callBack)
        moviewList.clear()
        moviewList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
       return moviewList.size
    }
}