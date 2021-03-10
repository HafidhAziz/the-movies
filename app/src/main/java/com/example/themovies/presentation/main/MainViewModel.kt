package com.example.themovies.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovies.data.MovieListEntity
import com.example.themovies.repository.AppRepository
import com.example.themovies.util.AppConstants
import com.example.themovies.util.Resource
import kotlinx.coroutines.launch

/**
 * Created by M Hafidh Abdul Aziz on 09/03/21.
 */

class MainViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _moviesData = MutableLiveData<Resource<MovieListEntity>>()
    val moviesData: LiveData<Resource<MovieListEntity>>
        get() = _moviesData

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _moviesData.postValue(Resource.loading(null))
            appRepository.getPopularMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    _moviesData.postValue(Resource.success(it.body()))
                } else _moviesData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            _moviesData.postValue(Resource.loading(null))
            appRepository.getUpcomingMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    _moviesData.postValue(Resource.success(it.body()))
                } else _moviesData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun fetchTopRatedMovies() {
        viewModelScope.launch {
            _moviesData.postValue(Resource.loading(null))
            appRepository.getTopRatedMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    _moviesData.postValue(Resource.success(it.body()))
                } else _moviesData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            _moviesData.postValue(Resource.loading(null))
            appRepository.getNowPlayingMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    _moviesData.postValue(Resource.success(it.body()))
                } else _moviesData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}