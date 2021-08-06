package com.garyjmj.tmdb.services

import com.garyjmj.tmdb.data.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    object ServiceBuilder {

        // Create logger
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // Create okHttp Client
        private val okHttp = OkHttpClient.Builder().addInterceptor(logger)


        // Create Retrofit Builder
        private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

        // Create Retrofit Instance
        private val retrofit = builder.build()

        fun <T> buildService(serviceType: Class<T>): T {
            return retrofit.create(serviceType)
        }
    }
}