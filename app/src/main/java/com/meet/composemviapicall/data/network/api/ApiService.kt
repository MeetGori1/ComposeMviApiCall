package com.meet.composemviapicall.data.network.api

import com.meet.composemviapicall.data.model.MealResponse
import com.meet.composemviapicall.data.network.client.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class ApiService {
    private val ktorClient = KtorClient.client

    suspend fun getRandomMeal(): MealResponse {
        val response: HttpResponse = ktorClient.get(HttpRoutes.RANDOM_MEALS)
        return response.body()
    }

    suspend fun getMealsBySearch(query: String): MealResponse {
        val response: HttpResponse = ktorClient.get("${HttpRoutes.MEALS_BY_SEARCH}?s=$query")
        return response.body()
    }
}