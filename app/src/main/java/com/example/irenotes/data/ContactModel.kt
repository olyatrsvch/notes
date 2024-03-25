package com.example.irenotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.irenotes.presentation.adapters.ContactItem

@Entity(tableName = "contacts_table")

data class ContactModel (
    //@PrimaryKey val number: Int,
    @PrimaryKey val name: String,
    val note: String,
        )

fun ContactModel.toContactItem(): ContactItem {
    return ContactItem(name = name, note = note)
}