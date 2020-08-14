package com.sunilkumardemoapp.mycontacts.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunilkumardemoapp.mycontacts.R
import com.sunilkumardemoapp.mycontacts.ui.home.model.Result

import com.sunilkumardemoapp.mycontacts.databinding.RecyclerviewContactsBinding


class ContactsAdapter : PagedListAdapter<Result, ContactsAdapter.ContactsViewHolder>(
    DIFF_CALLBACK
) {


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Result> =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

    inner class ContactsViewHolder(
        val binding: RecyclerviewContactsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_contacts,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.binding.viewModel = item
            holder.binding.executePendingBindings()
        }
    }

}