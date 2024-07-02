package com.meet.composemviapicall.ui.viewmodel

sealed class Intents {
    data object GetRandomMeals : Intents()
    data class GetSearchMeals(val query: String) : Intents()
}