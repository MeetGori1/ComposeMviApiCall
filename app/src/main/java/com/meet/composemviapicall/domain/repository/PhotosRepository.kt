package com.meet.composemviapicall.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.domain.api.ApiService
import kotlinx.coroutines.flow.Flow

object PhotosRepository {
    private val apiService = ApiService()

    fun getPhotos(): Flow<PagingData<Photos>> {
        return Pager(
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = { apiService.getPhotos() }
        ).flow
    }

    fun getSearchedPhotos(query: String): Flow<PagingData<Photos>> {
        return Pager(
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = { apiService.getSearchedPhotos(query) }
        ).flow
    }
}