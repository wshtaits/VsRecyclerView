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

package com.wshtaits.vsrecyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wshtaits.vsrecyclerview.adapter.VsRecyclerViewAdapter
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.SimpleItemsAdapter
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.SimpleNoDataItemsAdapter
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.ItemViewHolder
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.ItemsAdapter
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.NoDataItemsAdapter

class VsRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    private val vsAdapter = VsRecyclerViewAdapter()

    init {
        if (layoutManager == null) {
            layoutManager = LinearLayoutManager(context)
        }

        val attrsTypedArray = context.obtainStyledAttributes(attrs,
            R.styleable.VsRecyclerView
        )
        val hasStableIds = attrsTypedArray.getBoolean(R.styleable.VsRecyclerView_hasStableIds, false)
        attrsTypedArray.recycle()

        vsAdapter.setHasStableIds(hasStableIds)
        adapter = vsAdapter
    }

    fun <AdaptableData> setItem(data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        vsAdapter.setItem(data, adapter)
    }

    fun <AdaptableData> setItem(
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.setItem(
            data,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun <AdaptableData> setItems(dataCollection: Collection<AdaptableData>, adapter: ItemsAdapter<AdaptableData>) {
        vsAdapter.setItems(dataCollection, adapter)
    }

    fun <AdaptableData> setItems(
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.setItems(
            dataCollection,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun setNoDataItem(adapter: NoDataItemsAdapter) {
        vsAdapter.setNoDataItem(adapter)
    }

    fun setNoDataItem(
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.setNoDataItem(
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun setNoDataItems(adapter: NoDataItemsAdapter, itemCount: Int) {
        vsAdapter.setNoDataItems(adapter, itemCount)
    }

    fun setNoDataItems(
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.setNoDataItems(
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
            itemCount
        )
    }

    fun <AdaptableData> insertItem(data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        vsAdapter.insertItem(vsAdapter.itemCount, data, adapter)
    }

    fun <AdaptableData> insertItem(position: Int, data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        vsAdapter.insertItem(position, data, adapter)
    }

    fun <AdaptableData> insertItem(
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.insertItem(
            vsAdapter.itemCount,
            data,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun <AdaptableData> insertItem(
        position: Int,
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.insertItem(
            position,
            data,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun <AdaptableData> insertItems(dataCollection: Collection<AdaptableData>, adapter: ItemsAdapter<AdaptableData>) {
        vsAdapter.insertItems(vsAdapter.itemCount, dataCollection, adapter)
    }

    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ) {
        vsAdapter.insertItems(position, dataCollection, adapter)
    }

    fun <AdaptableData> insertItems(
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.insertItems(
            vsAdapter.itemCount,
            dataCollection,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.insertItems(
            position,
            dataCollection,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun insertNoDataItem(adapter: NoDataItemsAdapter) {
        vsAdapter.insertNoDataItem(vsAdapter.itemCount, adapter)
    }

    fun insertNoDataItem(position: Int, adapter: NoDataItemsAdapter) {
        vsAdapter.insertNoDataItem(position, adapter)
    }

    fun insertNoDataItem(
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.insertNoDataItem(
            vsAdapter.itemCount,
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun insertNoDataItem(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.insertNoDataItem(
            position,
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun insertNoDataItems(adapter: NoDataItemsAdapter, itemCount: Int) {
        vsAdapter.insertNoDataItems(vsAdapter.itemCount, adapter, itemCount)
    }

    fun insertNoDataItems(position: Int, adapter: NoDataItemsAdapter, itemCount: Int) {
        vsAdapter.insertNoDataItems(position, adapter, itemCount)
    }

    fun insertNoDataItems(
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.insertNoDataItems(
            vsAdapter.itemCount,
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
            itemCount
        )
    }

    fun insertNoDataItems(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.insertNoDataItems(
            position,
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
            itemCount
        )
    }

    fun <AdaptableData> changeToItem(position: Int, data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        vsAdapter.changeToItem(position, data, adapter)
    }

    fun <AdaptableData> changeToItem(
        position: Int,
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.changeToItem(
            position,
            data,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun <AdaptableData> changeToItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ) {
        vsAdapter.changeToItems(position, dataCollection, adapter)
    }

    fun <AdaptableData> changeToItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    ) {
        vsAdapter.changeToItems(
            position,
            dataCollection,
            SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun changeToNoDataItem(position: Int, adapter: NoDataItemsAdapter) {
        vsAdapter.changeToNoDataItem(position, adapter)
    }

    fun changeToNoDataItem(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.changeToNoDataItem(
            position,
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
        )
    }

    fun changeToNoDataItems(position: Int, adapter: NoDataItemsAdapter, itemCount: Int) {
        vsAdapter.changeToNoDataItems(position, adapter, itemCount)
    }

    fun changeToNoDataItems(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    ) {
        vsAdapter.changeToNoDataItems(
            position,
            SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
            itemCount
        )
    }

    fun removeItem(item: Any) {
        vsAdapter.removeItem(item)
    }

    fun removeItem(position: Int) {
        vsAdapter.removeItem(position)
    }

    fun removeItems(items: Collection<Any>) {
        vsAdapter.removeItems(items)
    }

    fun removeItems(position: Int, itemCount: Int) {
        vsAdapter.removeItems(position, itemCount)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        vsAdapter.moveItem(fromPosition, toPosition)
    }

    fun clearItems() {
        vsAdapter.clearItems()
    }
}