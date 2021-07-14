package com.archi4zlo.shoppinglist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.archi4zlo.shoppinglist.R
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

            fabAddShoppingItem.setOnClickListener {
                findNavController().navigate(
                    ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
                )
            }
        }
        catch (e: Exception){
            Log.e("TAGGGGG","onViewCreatedShoppingFragment")
            throw e
        }

    }
}