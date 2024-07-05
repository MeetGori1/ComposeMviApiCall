package com.meet.composemviapicall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val _state = MutableStateFlow<PhotosState>(PhotosState.Loading)
    val state: StateFlow<PhotosState> = _state

    private var currentQuery: String? = null
    val photos: Flow<PagingData<Photos>> = currentQuery?.let {
        PhotosRepository.getSearchedPhotos(it)
    } ?: PhotosRepository.getPhotos()

    fun processIntent(intent: Intents) {
        when (intent) {
            is Intents.GetRandomPhotos -> {
                currentQuery = null
                _state.value = PhotosState.Loading
            }
            is Intents.GetSearchedPhotos -> {
                currentQuery = intent.query
                _state.value = PhotosState.Loading
            }
        }
    }
}