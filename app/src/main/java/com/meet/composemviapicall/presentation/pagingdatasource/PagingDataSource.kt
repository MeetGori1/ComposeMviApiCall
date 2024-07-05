package com.meet.composemviapicall.presentation.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.domain.api.HttpRoutes
import com.meet.composemviapicall.domain.repository.PhotosRepository

class PagingDataSource(private val endPoint: String, private val query: String? = null) :
    PagingSource<Int, Photos>() {

    override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val page = params.key ?: 1
        try {
            val photos = when (endPoint) {
                HttpRoutes.GET_PHOTOS -> {
                    PhotosRepository.getPhotos(page, params.loadSize)
                }

                HttpRoutes.SEARCH_PHOTOS -> {
                    PhotosRepository.getSearchedPhotos(page, params.loadSize, query ?: "")
                }

                else -> emptyList()
            }

            return LoadResult.Page(
                data = photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}