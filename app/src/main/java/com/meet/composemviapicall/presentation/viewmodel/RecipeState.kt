package com.meet.composemviapicall.presentation.viewmodel

import com.meet.composemviapicall.data.model.Meals

sealed class RecipeState {
    data object Loading : RecipeState()
    data class Error(val message: String) : RecipeState()
    data class Success(val meals: List<Meals>) : RecipeState()
}