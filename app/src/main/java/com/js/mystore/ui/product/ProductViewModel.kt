package com.js.mystore.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.mystore.model.Product
import com.js.mystore.repository.ProductRepository
import com.js.mystore.utils.DateUtils.date
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepo: ProductRepository) : ViewModel() {

    private var _productListLiveData = MutableLiveData<List<Product>>()
    var productListLiveData: LiveData<List<Product>> = _productListLiveData

    private var _productLiveData = MutableLiveData<Product>()
    var pruductLiveData: LiveData<Product> = _productLiveData

    fun getProductAll() {
        viewModelScope.launch {
            _productListLiveData.value = productRepo.getProductAll(1, true)
                .sortedByDescending { product ->
                    product.productId
                }
        }
    }

    fun getProduct(productId: Long) {
        viewModelScope.launch {
            _productLiveData.value = productRepo.getProduct(productId)
        }
    }

    fun setProduct(product: String, productDesc: String) {
        viewModelScope.launch {
            var product = Product(null, 1, product, productDesc, true, date(), date())
            productRepo.setProduct(product)
            getProductAll()
        }
    }
}
