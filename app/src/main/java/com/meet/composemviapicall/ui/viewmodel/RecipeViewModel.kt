package com.meet.composemviapicall.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meet.composemviapicall.data.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val _state = MutableStateFlow<RecipeState>(RecipeState.Loading)
    val state: StateFlow<RecipeState> = _state

    fun processIntent(intent: Intents) {
        when (intent) {
            is Intents.GetRandomMeals -> getRandomMeals()
            is Intents.GetSearchMeals -> getSearchMeals(intent.query)
        }
    }

    private fun getRandomMeals() {
        viewModelScope.launch {
            _state.value = RecipeState.Loading
            try {
                val meals = ApiClient.getRandomMeals()
                _state.value = RecipeState.Success(meals)
            } catch (e: Exception) {
                _state.value = RecipeState.Error(e.message.toString())
            }
        }
    }

    private fun getSearchMeals(query: String) {
        viewModelScope.launch {
            _state.value = RecipeState.Loading
            try {
                val meals = ApiClient.getMealsBySearch(query)
                _state.value = RecipeState.Success(meals)
            } catch (e: Exception) {
                _state.value = RecipeState.Error(e.message.toString())
            }
        }
    }
}