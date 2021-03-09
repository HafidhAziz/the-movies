package com.example.themovies.di

import android.content.Context
import androidx.room.Room
import com.example.themovies.db.FavoriteMovieDao
import com.example.themovies.db.TheMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TheMovieDatabase {
        return Room.databaseBuilder(
            appContext,
            TheMovieDatabase::class.java,
            TheMovieDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideFavoriteMovieDao(database: TheMovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }
}