package com.alirizakaygusuz.internshipproject_beerlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.alirizakaygusuz.internshipproject_beerlist.R
import com.alirizakaygusuz.internshipproject_beerlist.databinding.FragmentBeerBinding
import com.alirizakaygusuz.internshipproject_beerlist.databinding.FragmentDetailsBinding
import com.alirizakaygusuz.internshipproject_beerlist.util.downloadFromUrl
import com.alirizakaygusuz.internshipproject_beerlist.util.placeholderProgressBar
import com.alirizakaygusuz.internshipproject_beerlist.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private var beerUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details , container , false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            beerUuid = DetailsFragmentArgs.fromBundle(it).id
        }

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        detailsViewModel.getDataFromDatabase(beerUuid)

        observeLiveData()

    }

    private fun observeLiveData() {
        detailsViewModel.selectedBeer.observe(viewLifecycleOwner, Observer { beer ->

            beer?.let {
                binding.selectedBeer = it

            }
        })
    }

}