package com.velmurugan.mvvmretrofitrecyclerviewkotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.MovieListDiffCallback
import com.MovieViewholder
import com.bumptech.glide.Glide
import com.velmurugan.mvvmretrofitrecyclerviewkotlin.databinding.AdapterMovieBinding

class MainAdapter: ListAdapter<Any,RecyclerView.ViewHolder>(MovieListDiffCallback()) {

    var movies = mutableListOf<Movie>()

    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewholder {
        var viewHolder : RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when(viewType){
            R.layout.adapter_movie -> viewHolder = MovieViewholder.create(inflater, parent)
        }
        return  (viewHolder as MovieViewholder?)!!
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        val item = getItem(position)
        var itemViewType = 0
        when(item){
            is Movie -> itemViewType = R.layout.adapter_movie
        }
        return itemViewType
    }
    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is MovieViewholder -> holder.bind(item as Movie)
        }
    }
}

