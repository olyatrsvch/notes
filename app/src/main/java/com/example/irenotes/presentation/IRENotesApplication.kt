package com.example.irenotes.presentation

import android.app.Application
import com.example.irenotes.data.ContactsRoomDB
import com.example.irenotes.domain.ContactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class IRENotesApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ContactsRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { ContactsRepository(database.contactsDao()) }
}