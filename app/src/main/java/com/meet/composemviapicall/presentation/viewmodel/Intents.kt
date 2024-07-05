package com.meet.composemviapicall.presentation.viewmodel

sealed class Intents {
    data object GetRandomPhotos : Intents()
    data class GetSearchedPhotos(val query: String) : Intents()
}