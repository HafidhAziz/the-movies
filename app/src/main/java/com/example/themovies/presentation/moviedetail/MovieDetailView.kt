package com.example.themovies.presentation.moviedetail

import com.example.themovies.data.Review

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

interface MovieDetailView {

    fun getArguments()
    fun getMovieDetail()
    fun setupUI()
    fun setupObserver()
    fun setDataToView()
    fun renderReviewList(reviews: List<Review>)
    fun setupToolbar()
    fun onClickFavorite()
    fun onClickUnFavorite()
}