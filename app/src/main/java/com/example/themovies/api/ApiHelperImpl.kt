package com.example.themovies.api

import com.example.themovies.data.Movie
import com.example.themovies.data.MovieListEntity
import com.example.themovies.data.ReviewEntity
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class ApiHelperImpl @Inject constructor(private val apiService: AppApiService) :
    ApiHelper {

    override suspend fun getPopularMoviesFromApi(apiKey: String): Response<MovieListEntity> =
        apiService.getPopularMovies(apiKey)

    override suspend fun getTopRatedMoviesFromApi(apiKey: String): Response<MovieListEntity> =
        apiService.getTopRatedMovies(apiKey)

    override suspend fun getNowPlayingMoviesFromApi(apiKey: String): Response<MovieListEntity> =
        apiService.getNowPlayingMovies(apiKey)

    override suspend fun getUpcomingMoviesFromApi(apiKey: String): Response<MovieListEntity> =
        apiService.getUpcomingMovies(apiKey)

    override suspend fun getMovieDetailFromApi(movieId: Int, apiKey: String): Response<Movie> =
        apiService.getMovieDetail(movieId, apiKey)

    override suspend fun getReviewListFromApi(
        movieId: Int,
        apiKey: String
    ): Response<ReviewEntity> =
        apiService.getMovieReview(movieId, apiKey)
}