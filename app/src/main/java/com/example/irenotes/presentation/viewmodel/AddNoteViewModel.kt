package com.example.irenotes.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.*
import com.example.irenotes.data.ContactModel
import com.example.irenotes.domain.ContactsRepository
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: ContactsRepository) : ViewModel() {

    val allContacts: LiveData<List<ContactModel>> = repository.allContacts.asLiveData()

    fun insert(contact: ContactModel) = viewModelScope.launch {
        repository.insert(contact)
    }


    @SuppressLint("Range")
    fun getContacts(context: Context): MutableList<String> {

        val contacts: MutableList<String> = ArrayList()

        val cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                Log.d("getContacts", "name: $name")
                if (name != null) {
                    contacts.add(name)
                }
            } while (cursor.moveToNext())
            cursor.close()
        }
        refreshContacts(contacts)
        return contacts
    }

    private fun refreshContacts(contacts: List<String>) {
        allContacts.value?.filterNot {
            contacts.contains(it.name)
        }?.forEach { model ->
            viewModelScope.launch {
                repository.delete(model)
            }
        }
    }


}

class AddNoteViewModelFactory(private val repository: ContactsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddNoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}