package com.example.themovies.presentation.moviedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovies.data.Movie
import com.example.themovies.data.ReviewEntity
import com.example.themovies.repository.AppRepository
import com.example.themovies.util.AppConstants
import com.example.themovies.util.Resource
import kotlinx.coroutines.launch

/**
 * Created by M Hafidh Abdul Aziz on 10/03/21.
 */

class MovieDetailViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private var movieId: Int = 0

    private var movieName: String = ""
    fun getMovieName(): String = movieName

    private val _movieDetailData = MutableLiveData<Resource<Movie>>()
    val movieDetailData: LiveData<Resource<Movie>>
        get() = _movieDetailData

    private val _reviewData = MutableLiveData<Resource<ReviewEntity>>()
    val reviewData: LiveData<Resource<ReviewEntity>>
        get() = _reviewData

    fun initExtrasData(movieId: Int, movieName: String) {
        this.movieId = movieId
        this.movieName = movieName
    }

    fun fetchMovieDetail() {
        viewModelScope.launch {
            _movieDetailData.postValue(Resource.loading(null))
            appRepository.getMovieDetail(movieId, AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    _movieDetailData.postValue(Resource.success(it.body()))
                } else _movieDetailData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun fetchReviewList() {
        viewModelScope.launch {
            _reviewData.postValue(Resource.loading(null))
            appRepository.getReviewList(movieId, AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    _reviewData.postValue(Resource.success(it.body()))
                } else _reviewData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}