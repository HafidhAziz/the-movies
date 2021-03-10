package com.example.themovies.presentation.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.db.FavoriteMovie
import com.example.themovies.util.AppConstants

/**
 * Created by M Hafidh Abdul Aziz on 11/03/21.
 */

class FavoriteMovieListAdapter(
    private val favMovie: ArrayList<FavoriteMovie>,
    private val context: Context
) : RecyclerView.Adapter<FavoriteMovieListAdapter.DataViewHolder>() {

    var listener: ClickItemListener? = null

    class DataViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemMovieBinding = DataBindingUtil.bind(itemView)!!
        var listener: ClickItemListener? = null
        var drawable = CircularProgressDrawable(context)

        fun bind(favMovie: FavoriteMovie) {
            drawable.setColorSchemeColors(
                R.color.royal_blue,
                R.color.white,
                R.color.white_link_water
            )
            drawable.centerRadius = 30f
            drawable.strokeWidth = 5f
            drawable.start()

            binding.tvTitleMovie.text = favMovie.title
            binding.tvReleaseDate.text = favMovie.releaseDate
            binding.tvOverview.text = favMovie.overview
            Glide.with(binding.imageMovie.context)
                .load(AppConstants.BASE_IMAGE_URL + "" + favMovie.imageUrl)
                .placeholder(drawable)
                .into(binding.imageMovie)
            itemView.setOnClickListener {
                listener?.onClickItemListener(favMovie)
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

    override fun getItemCount(): Int = favMovie.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.listener = listener
        holder.bind(favMovie[position])
    }

    fun addData(list: List<FavoriteMovie>) {
        favMovie.clear()
        favMovie.addAll(list)
    }

    fun setClickItemListener(listener: ClickItemListener) {
        this.listener = listener
    }

    interface ClickItemListener {
        fun onClickItemListener(favMovie: FavoriteMovie)
    }
}