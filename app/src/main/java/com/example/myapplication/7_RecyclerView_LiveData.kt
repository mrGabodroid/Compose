package com.example.myapplication

import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Text
import androidx.ui.livedata.observeAsState

@Composable
fun ShoppingCart(
    shoppingCart: LiveData<List<String>>
) {
    val products = shoppingCart.observeAsState(emptyList())

    AdapterList(data = products.value) { product ->
        Text(product)
        // ViewHolder, etc.
    }
}
