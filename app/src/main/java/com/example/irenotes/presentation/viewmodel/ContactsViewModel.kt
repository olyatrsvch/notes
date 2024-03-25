package com.example.irenotes.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.Context
import android.provider.CalendarContract.Attendees.query
import android.provider.CalendarContract.Reminders.query
import android.provider.ContactsContract
import android.util.Log
import androidx.core.content.ContentResolverCompat.query
import androidx.lifecycle.*
import com.example.irenotes.data.ContactModel
import com.example.irenotes.domain.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    val allContacts: LiveData<List<ContactModel>> = repository.allContacts.asLiveData()

}

class ContactsViewModelFactory(private val repository: ContactsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}