package com.example.themovies.di

import com.example.themovies.db.FavoriteMovieDataSource
import com.example.themovies.db.FavoriteMovieLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

@InstallIn(ApplicationComponent::class)
@Module
abstract class FavoriteMovieModule {

    @Singleton
    @Binds
    abstract fun bindFavoriteMovie(impl: FavoriteMovieLocalDataSource): FavoriteMovieDataSource
}