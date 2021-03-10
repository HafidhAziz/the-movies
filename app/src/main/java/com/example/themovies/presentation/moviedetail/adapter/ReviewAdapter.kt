package com.example.themovies.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.data.Review
import com.example.themovies.databinding.ItemUserReviewBinding
import com.example.themovies.util.AppConstants

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class ReviewAdapter(
    private val review: ArrayList<Review>
) : RecyclerView.Adapter<ReviewAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemUserReviewBinding = DataBindingUtil.bind(itemView)!!

        fun bind(review: Review) {
            binding.tvRating.text = review.authorDetails?.rating.toString()
            binding.tvAuthor.text = review.authorDetails?.username
            binding.tvContent.text = review.content
            Glide.with(binding.imageAuthor.context)
                .load(String.format(AppConstants.BASE_URL, review.authorDetails?.avatarPath))
                .into(binding.imageAuthor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_review, parent,
                false
            )
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