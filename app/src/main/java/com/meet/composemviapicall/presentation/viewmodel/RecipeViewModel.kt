package com.meet.composemviapicall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meet.composemviapicall.domain.repository.MealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val _state = MutableStateFlow<RecipeState>(RecipeState.Loading)
    val state: StateFlow<RecipeState> = _state

    fun processIntent(intent: Intents) {
        when (intent) {
            is Intents.GetRandomMeals -> getSearchMeals()
        }
    }



    private fun getSearchMeals() {
        viewModelScope.launch {
            _state.value = RecipeState.Loading
            try {
                val meals = MealsRepository.getPhotos()
                _state.value = RecipeState.Success(meals)
            } catch (e: Exception) {
                _state.value = RecipeState.Error(e.message.toString())
            }
        }
    }
}