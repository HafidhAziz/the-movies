package com.example.themovies.presentation.moviedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.data.Review
import com.example.themovies.databinding.ItemUserReviewBinding
import com.example.themovies.util.AppConstants

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class ReviewAdapter(
    private val review: ArrayList<Review>,
    private val context: Context
) : RecyclerView.Adapter<ReviewAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemUserReviewBinding = DataBindingUtil.bind(itemView)!!
        var drawable = CircularProgressDrawable(context)

        fun bind(review: Review) {
            drawable.setColorSchemeColors(
                R.color.royal_blue,
                R.color.white,
                R.color.white_link_water
            )
            drawable.centerRadius = 30f
            drawable.strokeWidth = 5f
            drawable.start()
            review.author_details?.rating.let {
                binding.tvRating.text = it.toString()
                if (it == null) {
                    binding.tvRating.visibility = View.GONE
                }
            }
            binding.tvAuthor.text = review.author_details?.username
            binding.tvContent.text = review.content
            Glide.with(binding.imageAuthor.context)
                .load(AppConstants.BASE_IMAGE_URL + "" + review.author_details?.avatar_path)
                .placeholder(drawable)
                .into(binding.imageAuthor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_review, parent,
                false
            ), context
        )

    override fun getItemCount(): Int = review.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(review[position])
    }

    fun addData(list: List<Review>) {
        review.clear()
        review.addAll(list)
    }
}