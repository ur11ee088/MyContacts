package com.sunilkumardemoapp.mycontacts.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sunilkumardemoapp.mycontacts.R
import com.sunilkumardemoapp.mycontacts.base.BaseFragment
import com.sunilkumardemoapp.mycontacts.databinding.FragmentHomeBinding
import com.sunilkumardemoapp.mycontacts.factory.ContactsViewModelFactory
import com.sunilkumardemoapp.mycontacts.ui.home.adapter.ContactsAdapter
import org.kodein.di.generic.instance

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val mAdapter = ContactsAdapter()
    private val factory: ContactsViewModelFactory by instance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.mPagedList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })

        binding.recyclerViewContacts.adapter = mAdapter


        binding.swipeRefresh.setOnRefreshListener {
            viewModel.mPagedList.observe(viewLifecycleOwner, Observer {
                mAdapter.submitList(it)
                binding.swipeRefresh.isRefreshing = false
            })
        }
    }
}