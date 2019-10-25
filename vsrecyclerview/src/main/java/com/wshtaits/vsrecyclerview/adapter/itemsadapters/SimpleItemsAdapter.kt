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

package com.wshtaits.vsrecyclerview.adapter.itemsadapters

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.ItemViewHolder
import com.wshtaits.vsrecyclerview.adapter.itemsadapters.base.ItemsAdapter

open class SimpleItemsAdapter<AdaptableData> : ItemsAdapter<AdaptableData> {

    private val onCreateAction: ItemViewHolder.() -> Unit
    private val onBindAction: ItemViewHolder.(AdaptableData) -> Unit

    constructor(
        @LayoutRes layoutResId: Int,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit = { _ -> }
    ) : super(layoutResId) {
        this.onCreateAction = onCreateAction
        this.onBindAction = onBindAction
    }

    constructor(
        itemViewFactoryFunction: (parent: ViewGroup) -> View,
        onCreateAction: ItemViewHolder.() -> Unit = {},
        onBindAction: ItemViewHolder.(AdaptableData) -> Unit = { _ -> }
    ) : super(itemViewFactoryFunction) {
        this.onCreateAction = onCreateAction
        this.onBindAction = onBindAction
    }

    override fun ItemViewHolder.onCreateViewHolder(): Unit = onCreateAction()

    override fun ItemViewHolder.onBindViewHolder(data: AdaptableData): Unit = onBindAction(data)
}