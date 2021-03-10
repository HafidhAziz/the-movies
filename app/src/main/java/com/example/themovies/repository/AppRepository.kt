package com.example.themovies.repository

import com.example.themovies.api.ApiHelper
import javax.inject.Inject

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class AppRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getPopularMovies(apiKey: String) = apiHelper.getPopularMoviesFromApi(apiKey)
    suspend fun getTopRatedMovies(apiKey: String) = apiHelper.getTopRatedMoviesFromApi(apiKey)
    suspend fun getNowPlayingMovies(apiKey: String) = apiHelper.getNowPlayingMoviesFromApi(apiKey)
    suspend fun getUpcomingMovies(apiKey: String) = apiHelper.getUpcomingMoviesFromApi(apiKey)

    suspend fun getMovieDetail(movieId: Int, apiKey: String) =
        apiHelper.getMovieDetailFromApi(movieId, apiKey)

    suspend fun getReviewList(movieId: Int, apiKey: String) =
        apiHelper.getReviewListFromApi(movieId, apiKey)
}