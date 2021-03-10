package com.example.themovies.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.data.Movie
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.util.AppConstants

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class MovieListAdapter(
    private val movie: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() {

    var listener: ClickItemListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemMovieBinding = DataBindingUtil.bind(itemView)!!
        var listener: ClickItemListener? = null

        fun bind(movie: Movie) {
            binding.tvTitleMovie.text = movie.originalTitle
            binding.tvReleaseDate.text = movie.releaseDate
            Glide.with(binding.imageMovie.context)
                .load(String.format(AppConstants.BASE_URL, movie.posterPath))
                .into(binding.imageMovie)
            itemView.setOnClickListener {
                listener?.onClickItemListener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent,
                false
            )
        )

    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.listener = listener
        holder.bind(movie[position])
    }

    fun addData(list: List<Movie>) {
        movie.clear()
        movie.addAll(list)
    }

    fun setClickItemListener(listener: ClickItemListener) {
        this.listener = listener
    }

    interface ClickItemListener {
        fun onClickItemListener(movie: Movie)
    }
}