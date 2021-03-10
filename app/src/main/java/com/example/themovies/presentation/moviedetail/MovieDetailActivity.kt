package com.example.themovies.presentation.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.data.Movie
import com.example.themovies.data.Review
import com.example.themovies.databinding.ActivityMovieDetailBinding
import com.example.themovies.presentation.moviedetail.adapter.ReviewAdapter
import com.example.themovies.util.AppConstants
import com.example.themovies.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var reviewListAdapter: ReviewAdapter
    private lateinit var drawable: CircularProgressDrawable

    companion object {

        fun startThisActivity(
            context: Context,
            movieId: Int,
            movieName: String
        ) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(AppConstants.EXTRA_MOVIE_ID, movieId)
            intent.putExtra(AppConstants.EXTRA_MOVIE_NAME, movieName)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        getArguments()
        getMovieDetail()
        setupToolbar()
        setupUI()
        setupObserver()
    }

    override fun getArguments() {
        viewModel.initExtrasData(
            movieId = intent?.getIntExtra(AppConstants.EXTRA_MOVIE_ID, 0) ?: 0,
            movieName = intent?.getStringExtra(AppConstants.EXTRA_MOVIE_NAME).orEmpty()
        )
    }

    override fun getMovieDetail() {
        viewModel.fetchMovieDetail()
    }

    override fun setupUI() {
        val reviewListLayoutManager = LinearLayoutManager(this)
        reviewListLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvReview.layoutManager = reviewListLayoutManager
        reviewListAdapter = ReviewAdapter(arrayListOf(), this)
        binding.rvReview.adapter = reviewListAdapter
    }

    override fun setupObserver() {
        viewModel.movieDetailData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movieDetail ->
                        binding.movieDetailContainer.visibility = View.VISIBLE
                        setDataToView(movieDetail)
                        viewModel.fetchReviewList()
                    }
                }
                Status.LOADING -> {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        movieDetailContainer.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.reviewData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBarReview.visibility = View.GONE
                    it.data?.results?.let { reviews ->
                        renderReviewList(reviews)
                        binding.rvReview.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    binding.apply {
                        progressBarReview.visibility = View.VISIBLE
                        rvReview.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding.progressBarReview.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun setDataToView(movieDetail: Movie) {
        drawable = CircularProgressDrawable(this)
        drawable.setColorSchemeColors(
            R.color.royal_blue,
            R.color.white,
            R.color.white_link_water
        )
        drawable.centerRadius = 30f
        drawable.strokeWidth = 5f
        drawable.start()

        binding.apply {
            tvTitleMovieDetail.text = movieDetail.original_title
            tvReleaseDate.text = movieDetail.release_date
            tvOverview.text = movieDetail.overview
            Glide.with(imageMovieDetail.context)
                .load(AppConstants.BASE_IMAGE_URL + "" + movieDetail.poster_path)
                .placeholder(drawable)
                .into(imageMovieDetail)
        }
    }

    override fun renderReviewList(reviews: List<Review>) {
        reviewListAdapter.addData(reviews)
        reviewListAdapter.notifyDataSetChanged()
    }

    override fun setupToolbar() {
        binding.toolbarMovieDetail.apply {
            tvTitle.text = viewModel.getMovieName()
            btnBack.visibility = View.VISIBLE
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnFav.visibility = View.GONE
        }
    }

    override fun onClickFavorite() {
        TODO("Not yet implemented")
    }
}