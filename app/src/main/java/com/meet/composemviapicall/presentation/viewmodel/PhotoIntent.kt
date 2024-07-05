package com.meet.composemviapicall.presentation.viewmodel

sealed class PhotoIntent {
    data object GetRandomPhotos : PhotoIntent()
    data class GetSearchedPhotos(val query: String) : PhotoIntent()
}