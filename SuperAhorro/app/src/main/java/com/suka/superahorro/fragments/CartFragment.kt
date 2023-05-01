package com.suka.superahorro.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suka.superahorro.R
import com.suka.superahorro.adapters.ShopItemAdapter
import com.suka.superahorro.database.AppDatabase
import com.suka.superahorro.database.ShopItemDao
import com.suka.superahorro.entities.ShopItem
import com.suka.superahorro.packages.createInputDialog

class CartFragment : Fragment() {

    lateinit var v : View

    private var db: AppDatabase? = null
    private var shopItemDao: ShopItemDao? = null
    var shopItems: MutableList<ShopItem> = mutableListOf<ShopItem>()

    lateinit var recShopItems : RecyclerView
    lateinit var adapter : ShopItemAdapter
    lateinit var btCardAdd : Button
    lateinit var txtCartTotal : TextView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cart, container, false)
        recShopItems = v.findViewById(R.id.recShopItems)
        btCardAdd = v.findViewById(R.id.btCardAdd)
        txtCartTotal = v.findViewById(R.id.txtCartTotal)
        return v
    }


    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        shopItemDao = db?.shopItemDao()
        shopItems = shopItemDao?.fetchAllCartItems() ?: mutableListOf<ShopItem>()


        btCardAdd.setOnClickListener{
            val dialog = Dialog(requireActivity())
            createInputDialog(dialog, "Nuevo Item", "") {

            }
        }

        adapter = ShopItemAdapter(shopItems) { position ->
            val action = CartFragmentDirections.actionCartFragmentToItemDetailFragment(shopItems[position].id)
            findNavController().navigate(action)
        }
        recShopItems.layoutManager = LinearLayoutManager(context)
        recShopItems.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        txtCartTotal.text = adapter.getCartDescription()
    }

}