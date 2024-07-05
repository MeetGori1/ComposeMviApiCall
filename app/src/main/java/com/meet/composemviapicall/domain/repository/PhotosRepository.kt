package com.meet.composemviapicall.domain.repository

import com.meet.composemviapicall.data.base.BaseModel
import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.domain.api.ApiService

object PhotosRepository {
    private val apiService = ApiService()

    suspend fun getPhotos(page: Int, perPage: Int): List<Photos> {
        return apiService.getPhotos(page,perPage)
    }

    suspend fun getSearchedPhotos(page: Int, perPage: Int,query: String): BaseModel {
        return apiService.getSearchedPhotos(query,page,perPage)
    }
}