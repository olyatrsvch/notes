package com.example.irenotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.irenotes.data.ContactModel
import com.example.irenotes.databinding.FragmentAddNoteBinding
import com.example.irenotes.presentation.IRENotesApplication
import com.example.irenotes.presentation.viewmodel.AddNoteViewModel
import com.example.irenotes.presentation.viewmodel.AddNoteViewModelFactory


class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    lateinit var navController: NavController
    private val viewModel: AddNoteViewModel by viewModels {
        AddNoteViewModelFactory((activity?.application as IRENotesApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(binding.root)
        val contacts = viewModel.getContacts(requireContext())

        with (binding) {
            autoComplete.setAdapter(ArrayAdapter(requireContext(), R.layout.autocomplete_item, contacts))
            autoComplete.threshold = 1
            btnCreate.setOnClickListener {
                viewModel.insert(ContactModel(
                    autoComplete.text.toString(), etNote.text.toString())
                )
                navController.navigate(R.id.action_addNoteFragment_to_contactsFragment)
            }
        }


    }
}