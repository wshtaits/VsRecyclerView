package com.wshtaits.vsrecyclerview.adapter.itemsadapters.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

class ItemViewFactory {

    private val itemViewFactoryFunction: (parent: ViewGroup) -> View

    constructor(factoryFunction: (parent: ViewGroup) -> View) {
        this.itemViewFactoryFunction = factoryFunction
    }

    constructor(@LayoutRes layoutResId: Int) {
        itemViewFactoryFunction = { parent -> LayoutInflater.from(parent.context).inflate(layoutResId, parent, false) }
    }

    fun createItemView(parent: ViewGroup): View = itemViewFactoryFunction(parent)
}