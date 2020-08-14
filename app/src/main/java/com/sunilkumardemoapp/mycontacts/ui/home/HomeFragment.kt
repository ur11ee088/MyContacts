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
        inflater.inflate(R.menu.main, menu)
        setUpSearchViewListener(menu)
    }
    private fun setUpSearchViewListener(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
        val searchView = searchItem.actionView as SearchView

        searchView.apply {

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null && newText.length >= 3) {

                        Log.e("ashsjhsjkhskjd","skjdhdk"+newText)


                    }



                    return false
                }

            })

        }
        searchView.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                return true
            }

        })

    }

    override fun onRecyclerViewItemClick(view: View, item: Result) {
        val bundle = bundleOf("details" to item)
        Navigation.findNavController(view)
            .navigate(R.id.nav_slideshow, bundle)
    }

}