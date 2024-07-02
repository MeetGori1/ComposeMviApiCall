package com.meet.composemviapicall.data.network

import com.meet.composemviapicall.data.model.MealResponse
import com.meet.composemviapicall.data.model.Meals
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object ApiClient {
    private val apiClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getRandomMeals(): List<Meals> {
        val url = "https://www.themealdb.com/api/json/v1/1/random.php"
        val response = apiClient.get(url)
        val meals = response.body<MealResponse>().meals
        return meals
    }

    suspend fun getMealsBySearch(query: String): List<Meals> {
        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s=$query"
        val response = apiClient.get(url)
        val meals = response.body<MealResponse>().meals
        return meals
    }

}