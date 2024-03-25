package com.example.irenotes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.irenotes.R
import com.example.irenotes.databinding.ContactItemBinding

class ContactsAdapter : ListAdapter<ContactItem, ContactsAdapter.ContactViewHolder>(ContactsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ContactViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ContactItemBinding.bind(item)

        fun bind(contact: ContactItem) = with (binding) {
            tvName.text = contact.name
            tvNote.text = contact.note
        }

        companion object {
            fun create(parent: ViewGroup): ContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.contact_item, parent, false)
                return ContactViewHolder(view)
            }
        }
    }

    class ContactsComparator : DiffUtil.ItemCallback<ContactItem>() {
        override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
            return oldItem.name == newItem.name
        }
    }
}