package com.example.vsrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_contact.*

class MainActivity : AppCompatActivity() {

    data class Contact(
        val name: String,
        val phone: String
    )

    val contacts = listOf(
        Contact("Alex", "(751) 053-9233"),
        Contact("Bob", "(520) 395-8395"),
        Contact("Carl", "(203) 595-2852"),
        Contact("Dan", "(182) 735-4068"),
        Contact("Ember", "(582) 015-3298"),
        Contact("Frank", "(205) 196-7230"),
        Contact("Gordon", "(293) 582-3985"),
        Contact("Harry", "(492) 359-3953")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        with(vs_recycler_view) {
            insertItem("Contacts", R.layout.item_title) { holder, title ->
                (holder.itemView as TextView).text = title
            }

            insertNoDataItem(R.layout.item_divider)

            insertItems(contacts, R.layout.item_contact) { holder, contact ->
                holder.name_tv.text = contact.name
                holder.phone_tv.text = contact.phone
            }
        }
    }
}