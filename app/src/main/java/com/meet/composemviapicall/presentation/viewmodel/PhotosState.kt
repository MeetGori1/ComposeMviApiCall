package com.meet.composemviapicall.presentation.viewmodel

import com.meet.composemviapicall.data.model.Photos

sealed class PhotosState {
    data object Loading : PhotosState()
    data class Error(val message: String) : PhotosState()
    data class Success(val photos: List<Photos>) : PhotosState()
}