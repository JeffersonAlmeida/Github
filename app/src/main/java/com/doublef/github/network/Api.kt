package com.doublef.github.network

import com.doublef.github.model.RepositoryItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("repositories?" +
            "client_id=9c2f4ab0b15d20d589b2&client_secret=432e656b7e72f6669167c98253e26d3423fe3f6f")
    fun fetchRepositories(): Deferred<Response<List<RepositoryItem>>>

}
