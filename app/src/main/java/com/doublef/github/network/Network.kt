package com.doublef.github.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    class Builder {

        private var baseUrl: String = API_BASE_URL

        fun baseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        fun build(): Api {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

            return retrofit.create(Api::class.java)
        }
    }

    companion object {
        const val API_BASE_URL = "https://api.github.com/"
    }
}
