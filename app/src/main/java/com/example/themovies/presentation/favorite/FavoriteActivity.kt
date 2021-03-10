package com.example.themovies.presentation.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovies.R
import com.example.themovies.databinding.ActivityFavoriteBinding
import com.example.themovies.db.FavoriteMovie
import com.example.themovies.db.FavoriteMovieDataSource
import com.example.themovies.presentation.favorite.adapter.FavoriteMovieListAdapter
import com.example.themovies.presentation.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), FavoriteView,
    FavoriteMovieListAdapter.ClickItemListener {

    @Inject
    lateinit var favoriteMovieDataSource: FavoriteMovieDataSource
    lateinit var binding: ActivityFavoriteBinding
    private lateinit var movieListAdapter: FavoriteMovieListAdapter

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, FavoriteActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)

        setupToolbar()
        setupUI()
        setupData()
    }

    override fun setupToolbar() {
        binding.toolbarFavorite.apply {
            tvTitle.text = getString(R.string.favorite_movie_label)
            btnBack.visibility = View.VISIBLE
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnFav.visibility = View.GONE
        }
    }

    override fun setupUI() {
        val favMovieListLayoutManager = LinearLayoutManager(this)
        favMovieListLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvFavoriteMovieList.layoutManager = favMovieListLayoutManager
        movieListAdapter = FavoriteMovieListAdapter(arrayListOf(), this)
        movieListAdapter.setClickItemListener(this)
        binding.rvFavoriteMovieList.adapter = movieListAdapter
    }

    override fun setupData() {
        favoriteMovieDataSource.getAllFavoriteMovies { it ->
            movieListAdapter.addData(it)
            movieListAdapter.notifyDataSetChanged()
        }
    }

    override fun onClickItemListener(favMovie: FavoriteMovie) {
        MovieDetailActivity.startThisActivity(this, favMovie.idMovie, favMovie.title)
    }
}