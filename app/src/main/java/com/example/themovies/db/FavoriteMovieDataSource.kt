package com.example.themovies.db

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

interface FavoriteMovieDataSource {
    fun getAllFavoriteMovies(callback: (List<FavoriteMovie>) -> Unit)
    fun addMovie(
        idMovie: String,
        imageUrl: String,
        title: String,
        releaseDate: String,
        overview: String
    )

    fun deleteMovie(movie: FavoriteMovie)
}