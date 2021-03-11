package com.example.themovies

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.themovies.db.FavoriteMovie
import com.example.themovies.db.FavoriteMovieDao
import com.example.themovies.db.TheMovieDatabase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by M Hafidh Abdul Aziz on 11/03/21.
 */

@RunWith(AndroidJUnit4::class)
class FavoriteMovieDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoriteMovieDao: FavoriteMovieDao
    private lateinit var db: TheMovieDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TheMovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        favoriteMovieDao = db.favoriteMovieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAll() {
        val favMovie = FavoriteMovie(123, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie)
        val favMovie2 = FavoriteMovie(234, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie2)
        val movies = favoriteMovieDao.getAll()
        assertEquals(movies.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun isItemExistsById() {
        val favMovie = FavoriteMovie(123, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie)
        val favMovie2 = FavoriteMovie(234, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie2)
        assertTrue(favoriteMovieDao.isItemExists(123))
    }

    @Test
    @Throws(Exception::class)
    fun isItemNotExistsById() {
        val favMovie = FavoriteMovie(123, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie)
        val favMovie2 = FavoriteMovie(234, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie2)
        assertFalse(favoriteMovieDao.isItemExists(456))
    }

    @Test
    @Throws(Exception::class)
    fun deleteItemById() {
        val favMovie = FavoriteMovie(123, "url", "title", "release", "overview")
        favoriteMovieDao.insert(favMovie)
        favoriteMovieDao.deleteItemById(123)
        val movies = favoriteMovieDao.getAll()
        assertTrue(movies.isEmpty())
    }
}