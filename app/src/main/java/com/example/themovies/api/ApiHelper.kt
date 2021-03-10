package com.example.themovies.api

import com.example.themovies.data.Movie
import com.example.themovies.data.MovieListEntity
import com.example.themovies.data.ReviewEntity
import retrofit2.Response

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

interface ApiHelper {
    suspend fun getPopularMoviesFromApi(apiKey: String): Response<MovieListEntity>
    suspend fun getTopRatedMoviesFromApi(apiKey: String): Response<MovieListEntity>
    suspend fun getNowPlayingMoviesFromApi(apiKey: String): Response<MovieListEntity>
    suspend fun getUpcomingMoviesFromApi(apiKey: String): Response<MovieListEntity>
    suspend fun getMovieDetailFromApi(movieId: Int, apiKey: String): Response<Movie>
    suspend fun getReviewListFromApi(movieId: Int, apiKey: String): Response<ReviewEntity>
}