package com.example.themovies.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovies.R
import com.example.themovies.data.Movie
import com.example.themovies.databinding.ActivityMainBinding
import com.example.themovies.presentation.main.adapter.MovieListAdapter
import com.example.themovies.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView, MovieListAdapter.ClickItemListener {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupToolbar()
        setupUI()
        setupObserver()
    }

    override fun setupUI() {
        val movieListLayoutManager = LinearLayoutManager(this)
        movieListLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvHomeMovieList.layoutManager = movieListLayoutManager
        movieListAdapter = MovieListAdapter(arrayListOf(), this)
        movieListAdapter.setClickItemListener(this)
        binding.rvHomeMovieList.adapter = movieListAdapter
    }

    override fun setupObserver() {
        viewModel.moviesData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.results?.let { movies ->
                        renderMovies(movies)
                        binding.rvHomeMovieList.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        rvHomeMovieList.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun renderMovies(movies: List<Movie>) {
        movieListAdapter.addData(movies)
        movieListAdapter.notifyDataSetChanged()
    }

    override fun setupToolbar() {
        binding.toolbarHome.apply {
            tvTitle.text = getString(R.string.app_name)
            btnBack.visibility = View.GONE
            btnFav.visibility = View.VISIBLE
        }
    }

    override fun onClickItemListener(movie: Movie) {
        TODO("Not yet implemented")
    }
}