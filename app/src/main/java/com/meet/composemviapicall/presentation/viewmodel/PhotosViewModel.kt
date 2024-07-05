package com.meet.composemviapicall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meet.composemviapicall.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val _state = MutableStateFlow<PhotosState>(PhotosState.Loading)
    val state: StateFlow<PhotosState> = _state

    fun processIntent(intent: Intents) {
        when (intent) {
            is Intents.GetRandomPhotos -> getRandomPhotos()
            is Intents.GetSearchedPhotos -> getSearchedPhotos(intent.query)
        }
    }

    private fun getRandomPhotos() {
        viewModelScope.launch {
            _state.value = PhotosState.Loading
            try {
                val meals = PhotosRepository.getPhotos()
                _state.value = PhotosState.Success(meals)
            } catch (e: Exception) {
                _state.value = PhotosState.Error(e.message.toString())
            }
        }
    }

    private fun getSearchedPhotos(query: String) {
        viewModelScope.launch {
            _state.value = PhotosState.Loading
            try {
                val meals = PhotosRepository.getSearchedPhotos(query = query)
                _state.value = PhotosState.Success(meals)
            } catch (e: Exception) {
                _state.value = PhotosState.Error(e.message.toString())
            }
        }
    }
}