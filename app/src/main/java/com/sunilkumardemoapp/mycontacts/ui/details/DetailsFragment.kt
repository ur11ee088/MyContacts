package com.sunilkumardemoapp.mycontacts.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sunilkumardemoapp.mycontacts.R
import com.sunilkumardemoapp.mycontacts.ui.home.model.Result

import com.sunilkumardemoapp.mycontacts.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var details = arguments?.getSerializable("details") as Result

        binding.imgCover.setImageURI(details.picture.large)
        binding.imgPoster.setImageURI(details.picture.medium)
        binding.textReleaseDate.text = details?.location.country
        binding.textTitle.text = details?.name.first+" - "+details.phone


    }
}