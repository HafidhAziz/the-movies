package com.example.themovies.api

import com.example.themovies.data.Movie
import com.example.themovies.data.MovieListEntity
import com.example.themovies.data.ReviewEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

interface AppApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieListEntity>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieListEntity>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieListEntity>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieListEntity>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Movie>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<ReviewEntity>
}