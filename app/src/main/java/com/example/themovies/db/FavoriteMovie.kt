package com.example.themovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

@Entity(tableName = "tb_favorite_movies")
data class FavoriteMovie(
    val idMovie: String,
    val imageUrl: String,
    val title: String,
    val releaseDate: String,
    val overview: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}