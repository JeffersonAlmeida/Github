package com.doublef.github.features.list

import com.doublef.github.model.RepositoryItem

interface ItemClickListener {
    fun onClickItem(repositoryItem: RepositoryItem)
}
