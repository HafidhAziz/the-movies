package com.example.themovies.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM tb_favorite_movies ORDER BY id DESC")
    fun getAll(): List<FavoriteMovie>

    @Insert
    fun insert(vararg moview: FavoriteMovie)

    @Delete
    fun delete(movie: FavoriteMovie)

}