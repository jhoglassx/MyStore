package com.js.mystore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.mystore.model.Product
import com.js.mystore.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private var _productLiveData = MutableLiveData<Product>()
    val productLiveData: LiveData<Product> = _productLiveData

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            _productLiveData.value = repository.getProduct(productId)
        }
    }

    fun setProduct(product: Product) {
        viewModelScope.launch {
            repository.setProduct(product)
        }
    }
}
