package com.example.themovies.db

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

class FavoriteMovieLocalDataSource @Inject constructor(private val favoriteMovieDao: FavoriteMovieDao) :
    FavoriteMovieDataSource {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getAllFavoriteMovies(callback: (List<FavoriteMovie>) -> Unit) {
        executorService.execute {
            val movies = favoriteMovieDao.getAll()
            mainThreadHandler.post { callback(movies) }
        }
    }

    override fun addMovie(
        idMovie: Int,
        imageUrl: String,
        title: String,
        releaseDate: String,
        overview: String
    ) {
        executorService.execute {
            favoriteMovieDao.insert(
                FavoriteMovie(
                    idMovie, imageUrl, title, releaseDate, overview
                )
            )
        }
    }

    override fun isItemExists(id: Int, callback: (Boolean) -> Unit) {
        executorService.execute {
            val isExist = favoriteMovieDao.isItemExists(id)
            mainThreadHandler.post { callback(isExist) }
        }
    }

    override fun deleteItemById(id: Int) {
        executorService.execute {
            favoriteMovieDao.deleteItemById(id)
        }
    }
}