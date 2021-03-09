package com.example.themovies.db

import javax.inject.Inject

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

class FavoriteMovieLocalDataSource @Inject constructor(private val favoriteMovie: FavoriteMovie) :
    FavoriteMovieDataSource {

    override fun getAllFavoriteMovies(callback: (List<FavoriteMovie>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun addMovie(
        idMovie: String,
        imageUrl: String,
        title: String,
        releaseDate: String,
        overview: String
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteMovie(movie: FavoriteMovie) {
        TODO("Not yet implemented")
    }
}