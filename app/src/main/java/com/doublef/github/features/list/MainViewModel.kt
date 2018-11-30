package com.doublef.github.features.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.doublef.github.model.RepositoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository : Repository) : ViewModel() {

    val listData: MutableLiveData<List<RepositoryItem>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val hadNetworkError: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchRepositories()
    }

    fun fetchRepositories() {
        showLoading()
        GlobalScope.launch(context = Dispatchers.Main) {
            val request = repository.fetchRepositories()
            val response = request.await()
            hideLoading()
            if (response.isSuccessful) {
                val repositoriesList = response.body()
                showData(repositoriesList)
            } else {
                showNetworkError()
            }
        }
    }

    private fun showNetworkError() {
        hadNetworkError.value = true
    }

    private fun showData(repositoriesList: List<RepositoryItem>?) {
        listData.value = repositoriesList
    }

    private fun hideLoading() {
        isLoading.value = false
    }

    private fun showLoading() {
        isLoading.value = true
    }
}

