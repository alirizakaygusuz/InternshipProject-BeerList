package com.alirizakaygusuz.internshipproject_beerlist.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.internshipproject_beerlist.adapter.BeerAdapter
import com.alirizakaygusuz.internshipproject_beerlist.databinding.FragmentBeerBinding
import com.alirizakaygusuz.internshipproject_beerlist.viewmodel.BeerViewModel


class BeerFragment : Fragment() {

    private lateinit var binding: FragmentBeerBinding
    private lateinit var beerViewModel: BeerViewModel
    private var beerAdapter = BeerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel::class.java)
        beerViewModel.refreshData()

        initializeRecyclerBeerList()

        refreshLayout()

        observeLiveData()

    }



    private fun initializeRecyclerBeerList() {
        binding.recyclerBeerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerBeerList.adapter = beerAdapter
    }

    private fun refreshLayout(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerBeerList.visibility = View.GONE


            beerViewModel.refreshFromAPI()

            binding.swipeRefreshLayout.isRefreshing = false

        }
    }

    private fun observeLiveData() {
        beerViewModel.beer.observe(viewLifecycleOwner, Observer { beerList ->
            beerList?.let {
                binding.recyclerBeerList.visibility = View.VISIBLE
                beerAdapter.updateBeerList(beerList)
            }
        })


    }
}