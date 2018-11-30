package com.doublef.github.util

import com.doublef.github.model.RepositoryItem

class JsonHelper {

    private val name = "Lorem Ipsum"
    private val desc = "Neque porro quisquam est qui dolorem ipsum"

    fun getMockedData(): MutableList<RepositoryItem> {
        val list: MutableList<RepositoryItem> = arrayListOf()
        for (i in 1..20)
            list.add(RepositoryItem(i.toString(), name, desc))
        return list
    }
}