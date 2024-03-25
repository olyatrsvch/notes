package com.example.irenotes.domain

import androidx.annotation.WorkerThread
import com.example.irenotes.data.ContactModel
import com.example.irenotes.data.ContactsDAO
import kotlinx.coroutines.flow.Flow

class ContactsRepository(private val contactsDao: ContactsDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allContacts: Flow<List<ContactModel>> = contactsDao.getAllContacts()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: ContactModel) {
        contactsDao.insert(contact)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(contact: ContactModel) {
        contactsDao.delete(contact)
    }

}