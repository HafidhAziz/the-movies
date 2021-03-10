package com.example.themovies.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovies.R
import com.example.themovies.data.Movie
import com.example.themovies.databinding.ActivityMainBinding
import com.example.themovies.presentation.favorite.FavoriteActivity
import com.example.themovies.presentation.main.adapter.MovieListAdapter
import com.example.themovies.presentation.moviedetail.MovieDetailActivity
import com.example.themovies.util.Status
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView, MovieListAdapter.ClickItemListener {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var state: State

    companion object {
        enum class State {
            POPULAR,
            UPCOMING,
            TOP_RATED,
            NOW_PLAYING
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        state = State.POPULAR

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

        binding.btnCategory.setOnClickListener {
            onClickCategory()
        }
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
            btnFav.setOnClickListener {
                onClickFavorite()
            }
        }
    }

    override fun onClickFavorite() {
        FavoriteActivity.startThisActivity(this)
    }

    override fun onClickCategory() {
        val dialog = BottomSheetDialog(this, R.style.TransparentBottomSheetDialogTheme)
        val tvOptionPopular: TextView?
        val tvOptionUpcoming: TextView?
        val tvOptionTopRated: TextView?
        val tvOptionNowPlaying: TextView?

        dialog.let { dg ->
            dg.setContentView(R.layout.dialog_category)
            tvOptionPopular = dg.findViewById(R.id.tv_option_popular)
            tvOptionUpcoming = dg.findViewById(R.id.tv_option_upcoming)
            tvOptionTopRated = dg.findViewById(R.id.tv_option_top_rated)
            tvOptionNowPlaying = dg.findViewById(R.id.tv_option_now_playing)

            when (state) {
                State.POPULAR -> tvOptionPopular?.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue
                    )
                )
                State.UPCOMING -> tvOptionUpcoming?.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue
                    )
                )
                State.TOP_RATED -> tvOptionTopRated?.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue
                    )
                )
                State.NOW_PLAYING -> tvOptionNowPlaying?.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue
                    )
                )
            }

            tvOptionPopular?.setOnClickListener {
                if (state != State.POPULAR) {
                    viewModel.fetchPopularMovies()
                    state = State.POPULAR
                    tvOptionPopular.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.royal_blue
                        )
                    )
                    tvOptionUpcoming?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionTopRated?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionNowPlaying?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                }
                dialog.dismiss()
            }

            tvOptionUpcoming?.setOnClickListener {
                if (state != State.UPCOMING) {
                    viewModel.fetchUpcomingMovies()
                    state = State.UPCOMING
                    tvOptionPopular?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionUpcoming.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.royal_blue
                        )
                    )
                    tvOptionTopRated?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionNowPlaying?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                }
                dialog.dismiss()
            }

            tvOptionTopRated?.setOnClickListener {
                if (state != State.TOP_RATED) {
                    viewModel.fetchTopRatedMovies()
                    state = State.TOP_RATED
                    tvOptionPopular?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionUpcoming?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionTopRated.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.royal_blue
                        )
                    )
                    tvOptionNowPlaying?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                }
                dialog.dismiss()
            }

            tvOptionNowPlaying?.setOnClickListener {
                if (state != State.NOW_PLAYING) {
                    viewModel.fetchNowPlayingMovies()
                    state = State.NOW_PLAYING
                    tvOptionPopular?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.royal_blue
                        )
                    )
                    tvOptionUpcoming?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionTopRated?.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    tvOptionNowPlaying.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.royal_blue
                        )
                    )
                }
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    override fun onClickItemListener(movie: Movie) {
        MovieDetailActivity.startThisActivity(this, movie.id, movie.original_title.orEmpty())
    }
}