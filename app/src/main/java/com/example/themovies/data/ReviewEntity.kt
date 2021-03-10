package com.example.themovies.data

import com.squareup.moshi.Json

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

data class ReviewEntity(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "total_pages")
    val total_pages: Int? = null,
    @Json(name = "total_results")
    val total_results: Int? = null,
    @Json(name = "results")
    val results: List<Review>? = null
)

data class Review(
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "created_at")
    val created_at: String? = null,
    @Json(name = "author_details")
    val author_details: AuthorDetails? = null
)

data class AuthorDetails(
    @Json(name = "username")
    val username: String? = null,
    @Json(name = "avatar_path")
    val avatar_path: String? = null,
    @Json(name = "rating")
    val rating: Int? = 0
)