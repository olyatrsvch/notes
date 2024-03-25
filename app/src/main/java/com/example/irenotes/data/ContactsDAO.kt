package com.example.irenotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDAO {

    @Query("SELECT * FROM contacts_table ORDER BY name ASC")
    fun getAllContacts(): Flow<List<ContactModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: ContactModel)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(contact: ContactModel)
}