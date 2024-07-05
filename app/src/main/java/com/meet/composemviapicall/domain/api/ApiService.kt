package com.meet.composemviapicall.domain.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.data.model.PhotosList
import com.meet.composemviapicall.domain.client.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path

class ApiService {
    private val ktorClient = KtorClient.client

    fun getPhotos(): PagingSource<Int, Photos> {
        return object : PagingSource<Int, Photos>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
                val page = params.key ?: 1
                try {
                    val response: HttpResponse = ktorClient.get {
                        url {
                            path(HttpRoutes.GET_PHOTOS)
                            parameters.append("page", page.toString())
                            parameters.append("per_page", params.loadSize.toString())
                        }
                    }
                    val photos = response.body<List<Photos>>()
                    return LoadResult.Page(
                        data = photos,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (photos.isEmpty()) null else page + 1
                    )
                } catch (e: Exception) {
                    return LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                }
            }
        }
    }

    fun getSearchedPhotos(query: String): PagingSource<Int, Photos> {return object : PagingSource<Int, Photos>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
            val page = params.key ?: 1
            try {
                val response: HttpResponse = ktorClient.get {
                    url {
                        path(HttpRoutes.END_POINT_SEARCH)
                        parameters.append("page", page.toString())
                        parameters.append("per_page", params.loadSize.toString())
                        parameters.append("query", query)
                    }
                }
                val photosList = response.body<PhotosList>()
                return LoadResult.Page(
                    data = photosList.data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (photosList.data.isEmpty()) null else page + 1
                )
            } catch (e: Exception) {
                return LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }
    }
    }
}