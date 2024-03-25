package com.example.irenotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
abstract class ContactsRoomDB : RoomDatabase() {

    abstract fun contactsDao(): ContactsDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ContactsRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ContactsRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsRoomDB::class.java,
                    "contacts_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}