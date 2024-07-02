package com.meet.composemviapicall.domain.repository

import com.meet.composemviapicall.data.model.Meals
import com.meet.composemviapicall.domain.api.ApiService

object MealsRepository {

    private val apiService = ApiService()
    suspend fun getRandomMeal(): ArrayList<Meals> {
        return apiService.getRandomMeal().meals
    }

    suspend fun getMealsBySearch(query: String): ArrayList<Meals> {
        return apiService.getMealsBySearch(query).meals
    }

}