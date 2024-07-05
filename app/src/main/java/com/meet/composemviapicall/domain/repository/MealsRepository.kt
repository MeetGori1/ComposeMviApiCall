package com.meet.composemviapicall.domain.repository

import com.meet.composemviapicall.data.model.Meals
import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.domain.api.ApiService

object MealsRepository {

    private val apiService = ApiService()

    suspend fun getPhotos(): List<Photos> {
        return apiService.getPhotos().data
    }

}