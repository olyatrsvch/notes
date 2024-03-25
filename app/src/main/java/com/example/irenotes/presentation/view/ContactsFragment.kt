package com.example.irenotes.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.irenotes.R
import com.example.irenotes.data.toContactItem
import com.example.irenotes.databinding.FragmentContactsBinding
import com.example.irenotes.presentation.IRENotesApplication
import com.example.irenotes.presentation.adapters.ContactsAdapter
import com.example.irenotes.presentation.viewmodel.ContactsViewModel
import com.example.irenotes.presentation.viewmodel.ContactsViewModelFactory

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var navController: NavController
    private val contactsViewModel: ContactsViewModel by viewModels {
        ContactsViewModelFactory((activity?.application as IRENotesApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(binding.root)
        val recyclerView = binding.recyclerView
        val adapter = ContactsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        contactsViewModel.allContacts.observe(viewLifecycleOwner) {contacts ->
            contacts?.let { items -> adapter.submitList(items.map { it.toContactItem() }) }
        }
        binding.fabAdd.setOnClickListener {
            navController.navigate(R.id.action_contactsFragment_to_addNoteFragment)
        }
    }
}