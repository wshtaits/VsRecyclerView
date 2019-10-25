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

package com.wshtaits.vsrecyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.AdapterItem
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.ItemViewHolder
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.ItemsAdapter
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.NoDataItemsAdapter

internal class VsRecyclerViewAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var items = mutableListOf<AdapterItem<*>>()
    private var viewTypeToItemsAdapter = mutableMapOf<Int, ItemsAdapter<*>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewTypeToItemsAdapter[viewType]!!.createViewHolder(parent)

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) = items[position].bind(viewHolder)

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun getItemId(position: Int): Long = items[position].id
    
    fun <AdaptableData> setItem(data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        clearItems()
        items.add(adapter.createAdapterItem(data))
        addItemAdapter(adapter)
        notifyDataSetChanged()
    }

    fun setNoDataItem(adapter: NoDataItemsAdapter): Unit = setItem(Unit, adapter)

    fun <AdaptableData> setItems(dataCollection: Collection<AdaptableData>, adapter: ItemsAdapter<AdaptableData>) {
        clearItems()
        items.addAll(adapter.createAdapterItems(dataCollection).toMutableList())
        addItemAdapter(adapter)
        notifyDataSetChanged()
    }

    fun setNoDataItems(adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        setItems(Array(itemCount, { Unit }).asList(), adapter)

    fun <AdaptableData> insertItem(position: Int, data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        val adapterItem = adapter.createAdapterItem(data)
        items.add(position, adapterItem)
        addItemAdapter(adapter)
        notifyItemInserted(position)
    }

    fun insertNoDataItem(position: Int, adapter: NoDataItemsAdapter): Unit = insertItem(position, Unit, adapter)

    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ) {
        val adapterItems = adapter.createAdapterItems(dataCollection)
        items.addAll(position, adapterItems)
        addItemAdapter(adapter)
        notifyItemRangeInserted(position, adapterItems.size)
    }

    fun insertNoDataItems(position: Int, adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        insertItems(position, Array(itemCount, { Unit }).asList(), adapter)

    fun <AdaptableData> changeToItem(position: Int, data: AdaptableData, adapter: ItemsAdapter<AdaptableData>) {
        val adapterItem = adapter.createAdapterItem(data)
        items[position] = adapterItem
        deleteUnusedAdapters()
        addItemAdapter(adapter)
        notifyItemChanged(position)
    }

    fun changeToNoDataItem(position: Int, adapter: NoDataItemsAdapter): Unit = changeToItem(position, Unit, adapter)

    fun <AdaptableData> changeToItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ) {
        val adapterItems = adapter.createAdapterItems(dataCollection)
        for ((index, adapterItem) in adapterItems.withIndex()) {
            items[position + index] = adapterItem
        }
        deleteUnusedAdapters()
        addItemAdapter(adapter)
        notifyItemRangeChanged(position, adapterItems.size)
    }

    fun changeToNoDataItems(position: Int, adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        changeToItems(position, Array(itemCount, { Unit }).asList(), adapter)

    fun removeItem(item: Any) {
        val removingItemPosition = items.indexOfFirst { adapterItem -> adapterItem.data == item }
        removeItem(removingItemPosition)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        deleteUnusedAdapters()
        notifyItemRemoved(position)
    }

    fun removeItems(items: Collection<Any>) {
        for (item in items) {
            val position = this.items.indexOfFirst { adapterItem -> adapterItem.data == item }
            this.items.removeAt(position)
            notifyItemRemoved(position)
        }
        deleteUnusedAdapters()
    }

    fun removeItems(position: Int, itemCount: Int) {
        for (i in 0 until itemCount) {
            items.removeAt(position)
        }
        deleteUnusedAdapters()
        notifyItemRangeRemoved(position, itemCount)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val movingItem = items[fromPosition]
        items.removeAt(fromPosition)
        items.add(toPosition, movingItem)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun clearItems() {
        items.clear()
        viewTypeToItemsAdapter.clear()
        notifyDataSetChanged()
    }

    private fun addItemAdapter(adapter: ItemsAdapter<*>) {
        viewTypeToItemsAdapter[adapter.viewType] = adapter
    }

    private fun deleteUnusedAdapters() {
        val usedViewTypeSet = mutableSetOf<Int>()
        items.forEach { item -> usedViewTypeSet.add(item.viewType) }
        viewTypeToItemsAdapter.keys
            .filter { viewType -> viewType !in usedViewTypeSet }
            .forEach { viewType -> viewTypeToItemsAdapter.remove(viewType) }
    }
}