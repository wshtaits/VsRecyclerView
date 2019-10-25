/*
 * Copyright (c) 2019 Shtaits Valeriy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wshtaits.vsrecyclerview.adapter.itemsadapters.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class ItemsAdapter<in AdaptableData> {

    val viewType by lazy { hashCode() }

    private val itemViewFactory: ItemViewFactory

    constructor(@LayoutRes layoutResId: Int) {
        itemViewFactory = ItemViewFactory(layoutResId)
    }

    constructor(itemViewFactoryFunction: (parent: ViewGroup) -> View) {
        itemViewFactory = ItemViewFactory(itemViewFactoryFunction)
    }

    internal fun createViewHolder(parent: ViewGroup): ItemViewHolder {
        val itemView = itemViewFactory.createItemView(parent)
        val itemViewHolder = ItemViewHolder(itemView)
        itemViewHolder.onCreateViewHolder()
        return itemViewHolder
    }

    internal fun createAdapterItems(adaptableDataCollection: Collection<AdaptableData>): List<AdapterItem<*>> =
        adaptableDataCollection.map { adaptableData -> AdapterItem(adaptableData, this) }

    internal fun createAdapterItem(data: AdaptableData): AdapterItem<*> = AdapterItem(data, this)

    open fun getItemId(data: AdaptableData): Long = RecyclerView.NO_ID

    open fun ItemViewHolder.onCreateViewHolder() { /* stub */ }

    open fun ItemViewHolder.onBindViewHolder(data: AdaptableData) { /* stub */ }
}