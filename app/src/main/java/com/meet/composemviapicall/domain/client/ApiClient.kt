package com.meet.composemviapicall.domain.client

import com.meet.composemviapicall.data.model.MealResponse
import com.meet.composemviapicall.data.model.Meals
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }

        defaultRequest {
            url("https://www.themealdb.com/api/json/v1/1/")
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 30000
            requestTimeoutMillis = 30000
            socketTimeoutMillis = 30000
        }

      /*  install(DefaultRequest) {
            header(NetworkURL.TIME_ZONE, AppHelper.getDefaultTimeZone())
            header(NetworkURL.PLATFORM, "mobile")
            header(NetworkURL.APP_PLATFORM, "android")
            header(NetworkURL.APP_VERSION, BuildConfig.VERSION_NAME)
            header(NetworkURL.CONTENT_TYPE, NetworkURL.CONTENT_TYPE_VALUE)

            // Add authorization header if token is available
            val token = User.token
            if (token != null) {
                header(NetworkURL.HEADER_AUTHORIZATION, "Bearer $token")
                header(NetworkURL.HEADER_TOKEN, token)
            } else {
                header(NetworkURL.ROLE, "customer")
            }
        }*/

    }
}