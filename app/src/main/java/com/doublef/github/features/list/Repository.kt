package com.doublef.github.features.list


import com.doublef.github.model.RepositoryItem
import com.doublef.github.network.Api
import kotlinx.coroutines.Deferred
import retrofit2.Response

class Repository(private val api: Api) {
    fun fetchRepositories(): Deferred<Response<List<RepositoryItem>>> {
        return api.fetchRepositories()
    }
}
