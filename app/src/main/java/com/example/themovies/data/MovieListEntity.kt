package com.example.themovies.data

import com.squareup.moshi.Json

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

data class MovieListEntity(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "total_pages")
    val total_pages: Int? = null,
    @Json(name = "total_results")
    val total_results: Int? = null,
    @Json(name = "results")
    val results: List<Movie>? = null
)

data class Movie(
    @Json(name = "poster_path")
    val poster_path: String? = null,
    @Json(name = "original_title")
    val original_title: String? = null,
    @Json(name = "overview")
    val overview: String? = null,
    @Json(name = "release_date")
    val release_date: String? = null
)