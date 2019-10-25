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
import android.view.View
import android.view.ViewGroup
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

        val attrsTypedArray = context.obtainStyledAttributes(attrs, R.styleable.VsRecyclerView)
        val hasStableIds = attrsTypedArray.getBoolean(R.styleable.VsRecyclerView_hasStableIds, false)
        attrsTypedArray.recycle()

        vsAdapter.setHasStableIds(hasStableIds)
        adapter = vsAdapter
    }

    fun <AdaptableData> setItem(data: AdaptableData, adapter: ItemsAdapter<AdaptableData>): Unit =
        vsAdapter.setItem(data, adapter)

    fun <AdaptableData> setItem(
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.setItem(data, SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction))

    fun <AdaptableData> setItem(
        data: AdaptableData,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.setItem(data, SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction))

    fun setNoDataItem(adapter: NoDataItemsAdapter) = vsAdapter.setNoDataItem(adapter)

    fun setNoDataItem(
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.setNoDataItem(SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction))

    fun setNoDataItem(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.setNoDataItem(SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction))

    fun <AdaptableData> setItems(
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ): Unit = vsAdapter.setItems(dataCollection, adapter)

    fun <AdaptableData> setItems(
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.setItems(dataCollection, SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction))

    fun <AdaptableData> setItems(
        dataCollection: Collection<AdaptableData>,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.setItems(
        dataCollection,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun setNoDataItems(adapter: NoDataItemsAdapter, itemCount: Int): Unit = vsAdapter.setNoDataItems(adapter, itemCount)

    fun setNoDataItems(
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.setNoDataItems(
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
        itemCount
    )

    fun setNoDataItems(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.setNoDataItems(
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction),
        itemCount
    )

    fun <AdaptableData> insertItem(position: Int, data: AdaptableData, adapter: ItemsAdapter<AdaptableData>): Unit =
        vsAdapter.insertItem(position, data, adapter)

    fun <AdaptableData> insertItem(
        position: Int,
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItem(position, data, SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction))

    fun <AdaptableData> insertItem(
        position: Int,
        data: AdaptableData,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItem(
        position,
        data,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun insertNoDataItem(position: Int, adapter: NoDataItemsAdapter): Unit =
        vsAdapter.insertNoDataItem(position, adapter)

    fun insertNoDataItem(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItem(
        position,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun insertNoDataItem(
        position: Int,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItem(
        position,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItemToStart(data: AdaptableData, adapter: ItemsAdapter<AdaptableData>): Unit =
        vsAdapter.insertItem(0, data, adapter)

    fun <AdaptableData> insertItemToStart(
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItem(
        0,
        data,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItemToStart(
        data: AdaptableData,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItem(
        0,
        data,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun insertNoDataItemToStart(adapter: NoDataItemsAdapter): Unit =
        vsAdapter.insertNoDataItem(0, adapter)

    fun insertNoDataItemToStart(
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItem(
        0,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun insertNoDataItemToStart(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItem(
        0,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItemToEnd(data: AdaptableData, adapter: ItemsAdapter<AdaptableData>): Unit =
        vsAdapter.insertItem(vsAdapter.itemCount, data, adapter)

    fun <AdaptableData> insertItemToEnd(
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItem(
        vsAdapter.itemCount,
        data,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItemToEnd(
        data: AdaptableData,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItem(
        vsAdapter.itemCount,
        data,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun insertNoDataItemToEnd(adapter: NoDataItemsAdapter): Unit =
        vsAdapter.insertNoDataItem(vsAdapter.itemCount, adapter)

    fun insertNoDataItemToEnd(
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItem(
        vsAdapter.itemCount,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun insertNoDataItemToEnd(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItem(
        vsAdapter.itemCount,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ): Unit = vsAdapter.insertItems(position, dataCollection, adapter)

    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItems(
        position,
        dataCollection,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItems(
        position,
        dataCollection,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun insertNoDataItems(position: Int, adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        vsAdapter.insertNoDataItems(position, adapter, itemCount)

    fun insertNoDataItems(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItems(
        position,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
        itemCount
    )

    fun insertNoDataItems(
        position: Int,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItems(
        position,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction),
        itemCount
    )

    fun <AdaptableData> insertItemsToStart(
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ): Unit = vsAdapter.insertItems(vsAdapter.itemCount, dataCollection, adapter)

    fun <AdaptableData> insertItemsToStart(
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItems(
        vsAdapter.itemCount,
        dataCollection,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItemsToStart(
        dataCollection: Collection<AdaptableData>,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItems(
        vsAdapter.itemCount,
        dataCollection,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun insertNoDataItemsToStart(adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        vsAdapter.insertNoDataItems(vsAdapter.itemCount, adapter, itemCount)

    fun insertNoDataItemsToStart(
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItems(
        vsAdapter.itemCount,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
        itemCount
    )

    fun insertNoDataItemsToStart(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItems(
        vsAdapter.itemCount,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction),
        itemCount
    )

    fun <AdaptableData> insertItemsToEnd(
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ): Unit = vsAdapter.insertItems(vsAdapter.itemCount, dataCollection, adapter)

    fun <AdaptableData> insertItemsToEnd(
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItems(
        vsAdapter.itemCount,
        dataCollection,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> insertItemsToEnd(
        dataCollection: Collection<AdaptableData>,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.insertItems(
        vsAdapter.itemCount,
        dataCollection,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun insertNoDataItemsToEnd(adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        vsAdapter.insertNoDataItems(vsAdapter.itemCount, adapter, itemCount)

    fun insertNoDataItemsToEnd(
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItems(
        vsAdapter.itemCount,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
        itemCount
    )

    fun insertNoDataItemsToEnd(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.insertNoDataItems(
        vsAdapter.itemCount,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction),
        itemCount
    )

    fun <AdaptableData> changeToItem(position: Int, data: AdaptableData, adapter: ItemsAdapter<AdaptableData>): Unit =
        vsAdapter.changeToItem(position, data, adapter)

    fun <AdaptableData> changeToItem(
        position: Int,
        data: AdaptableData,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.changeToItem(
        position,
        data,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> changeToItem(
        position: Int,
        data: AdaptableData,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.changeToItem(
        position,
        data,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun changeToNoDataItem(position: Int, adapter: NoDataItemsAdapter): Unit =
        vsAdapter.changeToNoDataItem(position, adapter)

    fun changeToNoDataItem(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.changeToNoDataItem(
        position,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun changeToNoDataItem(
        position: Int,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.changeToNoDataItem(
        position,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun <AdaptableData> changeToItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    ): Unit = vsAdapter.changeToItems(position, dataCollection, adapter)

    fun <AdaptableData> changeToItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.changeToItems(
        position,
        dataCollection,
        SimpleItemsAdapter(itemLayoutResId, onCreateAction, onBindAction)
    )

    fun <AdaptableData> changeToItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit
    ): Unit = vsAdapter.changeToItems(
        position,
        dataCollection,
        SimpleItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction)
    )

    fun changeToNoDataItems(position: Int, adapter: NoDataItemsAdapter, itemCount: Int): Unit =
        vsAdapter.changeToNoDataItems(position, adapter, itemCount)

    fun changeToNoDataItems(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.changeToNoDataItems(
        position,
        SimpleNoDataItemsAdapter(itemLayoutResId, onCreateAction, onBindAction),
        itemCount
    )

    fun changeToNoDataItems(
        position: Int,
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        itemCount: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.() -> Unit = {}
    ): Unit = vsAdapter.changeToNoDataItems(
        position,
        SimpleNoDataItemsAdapter(itemViewFactoryFunction, onCreateAction, onBindAction),
        itemCount
    )

    fun removeItem(item: Any): Unit = vsAdapter.removeItem(item)

    fun removeItem(position: Int): Unit = vsAdapter.removeItem(position)

    fun removeItems(items: Collection<Any>): Unit = vsAdapter.removeItems(items)

    fun removeItems(position: Int, itemCount: Int): Unit = vsAdapter.removeItems(position, itemCount)

    fun moveItem(fromPosition: Int, toPosition: Int): Unit = vsAdapter.moveItem(fromPosition, toPosition)

    fun clearItems(): Unit = vsAdapter.clearItems()
}