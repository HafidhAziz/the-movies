package com.example.themovies.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class TheMovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        const val DATABASE_NAME: String = "the_movie.db"
    }
}