package com.nikhil.sportzapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.sportzapi.data.Repository
import com.nikhil.sportzapi.model.SportzResponse
import com.nikhil.sportzapi.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<SportzResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<SportzResponse>> = _response

    fun fetchSportzResponse() = viewModelScope.launch {
        repository.getSportzData().collect { values ->
            _response.value = values
        }
    }
}