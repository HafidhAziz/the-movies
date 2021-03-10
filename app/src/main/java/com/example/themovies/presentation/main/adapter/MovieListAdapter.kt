package com.example.themovies.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.data.Movie
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.util.AppConstants

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class MovieListAdapter(
    private val movie: ArrayList<Movie>,
    private val context: Context
) : RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() {

    var listener: ClickItemListener? = null

    class DataViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemMovieBinding = DataBindingUtil.bind(itemView)!!
        var listener: ClickItemListener? = null
        var drawable = CircularProgressDrawable(context)

        fun bind(movie: Movie) {
            drawable.setColorSchemeColors(
                R.color.royal_blue,
                R.color.white,
                R.color.white_link_water
            )
            drawable.centerRadius = 30f
            drawable.strokeWidth = 5f
            drawable.start()

            binding.tvTitleMovie.text = movie.original_title
            binding.tvReleaseDate.text = movie.release_date
            binding.tvOverview.text = movie.overview
            Glide.with(binding.imageMovie.context)
                .load(AppConstants.BASE_IMAGE_URL + "" + movie.poster_path)
                .placeholder(drawable)
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
            ), context
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