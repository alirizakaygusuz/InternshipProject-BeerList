package com.alirizakaygusuz.internshipproject_beerlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.alirizakaygusuz.internshipproject_beerlist.R
import com.alirizakaygusuz.internshipproject_beerlist.databinding.BeerRowBinding
import com.alirizakaygusuz.internshipproject_beerlist.databinding.FragmentBeerBinding
import com.alirizakaygusuz.internshipproject_beerlist.model.Beer
import com.alirizakaygusuz.internshipproject_beerlist.util.downloadFromUrl
import com.alirizakaygusuz.internshipproject_beerlist.util.placeholderProgressBar
import com.alirizakaygusuz.internshipproject_beerlist.view.BeerFragmentDirections
import kotlinx.android.synthetic.main.beer_row.view.*

class BeerAdapter(val beerList: ArrayList<Beer>) :
    RecyclerView.Adapter<BeerAdapter.BeerViewHolder>(), BeerClickListener {


    class BeerViewHolder(var view: BeerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<BeerRowBinding>(inflater, R.layout.beer_row, parent, false)


        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {

        holder.view.beer = beerList.get(position)

        holder.view.clickListener = this

    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    //swiperefresh
    fun updateBeerList(newBeerList: List<Beer>) {
        beerList.clear()
        beerList.addAll(newBeerList)

        notifyDataSetChanged()
    }

    override fun onBeerClicked(v: View) {
        val id = v.txtBeerId.text.toString().toInt()

        val action = BeerFragmentDirections.actionBeerFragmentToDetailsFragment(id)

        Navigation.findNavController(v).navigate(action)

    }
}