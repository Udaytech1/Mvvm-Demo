package com.task.ui.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.task.data.model.MovieDataModel
class MovieListCallBack (private val oldList:  List<MovieDataModel.Movy>, private val newList: List<MovieDataModel.Movy>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return  oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList.get(newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldId = oldList.get(oldItemPosition)
        val newId = oldList.get(oldItemPosition)
        return oldId==newId
    }
}