package com.meet.composemviapicall.domain.repository

import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.domain.api.ApiService

object PhotosRepository {
    private val apiService = ApiService()

    suspend fun getPhotos(): List<Photos> {
        return apiService.getPhotos()
    }

    suspend fun getSearchedPhotos(query: String): List<Photos> {
        return apiService.getSearchedPhotos(query).data
    }
}