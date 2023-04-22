package com.suka.superahorro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suka.superahorro.R
import com.suka.superahorro.adapters.ShopItemAdapter
import com.suka.superahorro.entities.ShopItemsRepository

class CartFragment : Fragment() {

    lateinit var v : View
    lateinit var recShopItems : RecyclerView
    lateinit var adapter : ShopItemAdapter

    var shopItemsRepository : ShopItemsRepository = ShopItemsRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cart, container, false)
        recShopItems = v.findViewById(R.id.recShopItems)
        return v
    }


    override fun onStart() {
        super.onStart()

        adapter = ShopItemAdapter(shopItemsRepository.shopItems) { position ->
            val action = CartFragmentDirections.actionCartFragmentToItemDetailFragment(shopItemsRepository.shopItems[position])
            findNavController().navigate(action)
        }
        recShopItems.layoutManager = LinearLayoutManager(context)
        recShopItems.adapter = adapter
    }

}