# VsRecyclerView
The library that removes all boilerplate code allowing you to display lists with few lines of code.

## Gradle
```groovy
implementation 'com.wshtaits:vsrecyclerview:1.0.0'
```

## Quick example
```xml
<com.wshtaits.vsrecyclerview.VsRecyclerView
    id="@+id/vs_recycler_view"
    layout_width="match_parent"
    layout_height="match_parent" />
```
```kotlin
//No need to write Adapters, ViewHolders or anything else. 
//Just:
vs_recycler_view.insertItems(names, R.layout.item_name) { holder, name ->
    holder.name_tv.text = name
}
```

## Capabilities

Available methods with different parameters for various use cases:
```kotlin
class VsRecyclerView {

    fun setItem(...)
    fun setItems(...)
    fun setNoDataItem(...)
    fun setNoDataItems(...)
    
    fun insertItem(...)
    fun insertItems(...)
    fun insertNoDataItem(...)
    fun insertNoDataItems(...)
    
    fun changeToItem(...)
    fun changeToItems(...)
    fun changeToNoDataItem(...)
    fun changeToNoDataItems(...)
        
    fun removeItem(...)
    fun removeItems(...)
        
    fun moveItem(fromPosition: Int, toPosition: Int)
        
    fun clearItems()
}
```

Available parameters (consider `insertItems(...)` as an example):
```kotlin
class VsRecyclerView {
    ...
    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        adapter: ItemsAdapter<AdaptableData>
    )
    
    fun <AdaptableData> insertItems(
        position: Int,
        dataCollection: Collection<AdaptableData>,
        @LayoutRes itemLayoutResId: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder, AdaptableData) -> Unit
    )
    
    fun insertNoDataItems(position: Int, adapter: NoDataItemsAdapter, count: Int)
    
    fun insertNoDataItems(
        position: Int,
        @LayoutRes itemLayoutResId: Int,
        count: Int,
        onCreateAction: (ItemViewHolder) -> Unit = {},
        onBindAction: (ItemViewHolder) -> Unit = {}
    )
    ...
}
```

## Important

### Adapters
Each time you don’t pass an adapter explicitly, `VsRecyclerView` creates a new one with its own `viewType` (based on the `hashCode()` of the adapter) and therefore with a separate `ViewHolderPool`.
For example, two different adapters will be created here:
```kotlin
with(vsRecyclerView) {
    // creates 1st adapter
    insertItems(names, R.layout.item_name) { holder, name -> 
        holder.name_tv.text = name
    }
    
    // creates 2nd adapter
    insertItems(names, R.layout.item_name) { holder, name -> 
        holder.name_tv.text = name
    }
}
```
In such cases it's better to create an `ItemsAdapter` or **template ItemsAdapter** and pass it explicitly.

**ItemsAdapter example:**
```kotlin
class NamesAdapter : ItemsAdapter<String>(R.layout.item_contact) {

    //override optionaly
    override fun onCreateViewHolder(holder: ItemViewHolder) {
        print("onCreateViewHolder")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, data: String) {
        print("onBindViewHolder for $item")
    }
        
    //override optionaly
    override fun getItemId(data: String): Long {
        return data.hashCode().toLong()
    }
}
```
Usage:
```kotlin
fun showNames(names: List<String>) {
    val namesAdapter = NamesAdapter()
    with(vsRecyclerView) {
        setItems(names, namesAdapter)
        insertNoDataItem(R.layout.divider)
        insertItem("Bob", namesAdapter)
    }
}
```

**Template ItemsAdapters:**
```kotlin
class SimpleItemsAdapter<AdaptableData>(
    @LayoutRes private val layoutResId: Int,
    private val onCreateAction: (ItemViewHolder) -> Unit = {},
    private val onBindAction: (ItemViewHolder, AdaptableData) -> Unit = { _, _ -> }
)

class SimpleNoDataItemsAdapter(
    @LayoutRes private val layoutResId: Int,
    private val onCreateAction: (ItemViewHolder) -> Unit = {},
    private val onBindAction: (ItemViewHolder) -> Unit = {}
)

class OnCreateNoDataItemsAdapter(
    @LayoutRes private val layoutResId: Int,
    onCreateAction: (ItemViewHolder) -> Unit
)
```
Usage:
```kotlin
fun showNames(names: List<String>) {
    val namesAdapter = SimpleItemsAdapter(R.layout.item_name) { holder, name ->
        holder.name_tv.text = name
    }
    with(vsRecyclerView) {
        setItems(names, namesAdapter)
        insertNoDataItem(R.layout.divider)
        insertItem("Bob", namesAdapter)
    }
}
```

### ViewHolders
The library `ViewHolder` implements the `LayoutContainer` interface to cache views, so you need to reference the views through the `ViewHolder` instance, not through `ViewHolder.itemView` property.
Read the [article](https://proandroiddev.com/kotlin-android-extensions-using-view-binding-the-right-way-707cd0c9e648?gi=10614fba5558) by Umut Uz.

Example:
```kotlin
// WRONG:
recyclerView.insertItems(names, R.layout.item_name) { holder, name ->
    holder.itemView.name_tv = name
}

// RIGHT:
recyclerView.insertItems(names, R.layout.item_name) { holder, name ->
    holder.name_tv = name
}
```
But if your xml layout contains only one view, then you can refer to it using the `ViewHolder.itemView` or `ViewHolder.containerView` properties:
```kotlin
recyclerView.insertItems("Title", R.layout.item_title) { holder, title ->
    (holder.itemView as TextView).text = title
}
```

### Differens between standard and library `onCreateViewHolder` method
`OnCreateViewHolder` is just a callback in the library version. You don't need to create a `ViewHolder` inside it. The library does this for you.

### Stable id's
If your items have stable ids, you need to specify this in the xml and override the `getItemId(...)` method in the `ItemsAdapter` or **template ItemsAdapters**.
```xml
<com.wshtaits.vsrecyclerview.VsRecyclerView
    layout_width="match_parent"
    layout_height="match_parent"
    hasStableIds="true"/>
```

### Default layoutManager
`LinearLayoutManager` set by default. So if you need to use it, it is not necessary to specify it in xml.

### Custom functions
If the methods provided are not enough for you, then add them using extension functions:
```kotlin
fun VsRecyclerView.insertName(name: String) {
    insertItem(name, R.layout.item_name) { holder, name -> holder.name_tv.text = name }
}
```

# License
>Copyright (c) 2019 Shtaits Valeriy.
>
>Licensed under the Apache License, Version 2.0 (the "License");
>you may not use this file except in compliance with the License.
>You may obtain a copy of the License at
>
>http://www.apache.org/licenses/LICENSE-2.0
>
>Unless required by applicable law or agreed to in writing, software
>distributed under the License is distributed on an "AS IS" BASIS,
>WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
>See the License for the specific language governing permissions and
>limitations under the License.
