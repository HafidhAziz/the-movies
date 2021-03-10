package com.example.themovies.db

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

interface FavoriteMovieDataSource {
    fun getAllFavoriteMovies(callback: (List<FavoriteMovie>) -> Unit)
    fun addMovie(
        idMovie: Int,
        imageUrl: String,
        title: String,
        releaseDate: String,
        overview: String
    )

    fun deleteMovie(movie: FavoriteMovie)

    fun isItemExists(id: Int, callback: (Boolean) -> Unit)

    fun deleteItemById(id: Int)
}