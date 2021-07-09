package com.archi4zlo.shoppinglist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.archi4zlo.shoppinglist.MainCoroutineRule
import com.archi4zlo.shoppinglist.getOrAwaitValueTest
import com.archi4zlo.shoppinglist.other.Constants
import com.archi4zlo.shoppinglist.other.Status
import com.archi4zlo.shoppinglist.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup(){
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field, returned error`(){
        viewModel.insertShoppingItem("name","","3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNoHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with too long name, returned error`(){
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH+1){
                append(1)
            }
        }
        viewModel.insertShoppingItem(string,"5","3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNoHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with too long price, returned error`(){
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH+1){
                append(1)
            }
        }
        viewModel.insertShoppingItem("name","5",string)
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNoHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with high amount, returned error`(){
        viewModel.insertShoppingItem("name","99999999999999999","3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNoHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with valid input, returned success`(){
        viewModel.insertShoppingItem("name","5","3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNoHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}