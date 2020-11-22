package com.sunilkumardemoapp.mycontacts.ui.home

import android.app.SearchManager
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sunilkumardemoapp.mycontacts.R
import com.sunilkumardemoapp.mycontacts.base.BaseFragment
import com.sunilkumardemoapp.mycontacts.base.RecyclerViewClickListener
import com.sunilkumardemoapp.mycontacts.databinding.FragmentHomeBinding
import com.sunilkumardemoapp.mycontacts.factory.ContactsViewModelFactory
import com.sunilkumardemoapp.mycontacts.ui.home.adapter.ContactsAdapter
import com.sunilkumardemoapp.mycontacts.ui.home.model.Result
import org.kodein.di.generic.instance

class HomeFragment : BaseFragment(),RecyclerViewClickListener<Result> {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val mAdapter = ContactsAdapter()
    private val factory: ContactsViewModelFactory by instance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        setViewModel(viewModel)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.mPagedList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })
        mAdapter.listener = this

        binding.recyclerViewContacts.adapter = mAdapter


        binding.swipeRefresh.setOnRefreshListener {
            viewModel.mPagedList.observe(viewLifecycleOwner, Observer {
                mAdapter.submitList(it)
                binding.swipeRefresh.isRefreshing = false
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onRecyclerViewItemClick(view: View, item: Result) {
        val bundle = bundleOf("details" to item)
        Navigation.findNavController(view)
            .navigate(R.id.nav_slideshow, bundle)
    }

}