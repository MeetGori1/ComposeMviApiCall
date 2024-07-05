package com.meet.composemviapicall.domain.api

import com.meet.composemviapicall.data.model.Photos
import com.meet.composemviapicall.data.model.PhotosList
import com.meet.composemviapicall.domain.client.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path

class ApiService {
    private val ktorClient = KtorClient.client

    suspend fun getPhotos(): List<Photos>{
        val response: HttpResponse = ktorClient.get {
            url {
                path(HttpRoutes.GET_PHOTOS)
                parameters.append("page", "1")
                parameters.append("per_page", "20")
            }
        }
        return response.body()
    }

    suspend fun getSearchedPhotos(query: String): PhotosList{
        val response: HttpResponse = ktorClient.get {
            url {
                path(HttpRoutes.END_POINT_SEARCH)
                parameters.append("page", "1")
                parameters.append("per_page", "10")
                parameters.append("query", query)
            }
        }
        return response.body()
    }
}