package com

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.velmurugan.mvvmretrofitrecyclerviewkotlin.Movie
import com.velmurugan.mvvmretrofitrecyclerviewkotlin.R
import com.velmurugan.mvvmretrofitrecyclerviewkotlin.databinding.AdapterMovieBinding as AdapterMovieBinding

class MovieViewholder(val binding: AdapterMovieBinding) :RecyclerView.ViewHolder(binding.root){
    companion object{
        fun create(inflater: LayoutInflater,
                   viewGroup: ViewGroup
        ) : MovieViewholder {
            val binding = DataBindingUtil.inflate<AdapterMovieBinding>(inflater, R.layout.adapter_movie , viewGroup ,false)
            return MovieViewholder(binding)
        }
    }
    private val photo: ImageView = binding.imageview

    fun bind(post: Movie ){
        print("Bind")



        binding.name.text = post.name
        Glide.with(photo).load(post.imageUrl).into(photo)

    }

}
