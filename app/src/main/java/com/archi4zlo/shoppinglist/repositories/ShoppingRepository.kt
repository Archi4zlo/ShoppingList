package com.archi4zlo.shoppinglist.repositories

import androidx.lifecycle.LiveData
import com.archi4zlo.shoppinglist.data.local.ShoppingItem
import com.archi4zlo.shoppinglist.data.remote.response.ImageResponse
import com.archi4zlo.shoppinglist.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}