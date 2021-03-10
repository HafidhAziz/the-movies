package com.example.themovies.presentation.main

import com.example.themovies.data.Movie

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

interface MainView {
    fun setupUI()
    fun setupObserver()
    fun renderMovies(movies: List<Movie>)
    fun setupToolbar()
    fun onClickFavorite()
    fun onClickCategory()
}