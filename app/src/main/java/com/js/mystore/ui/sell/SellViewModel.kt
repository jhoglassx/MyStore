package com.js.mystore.ui.sell

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.mystore.model.Product
import com.js.mystore.model.ProductSell
import com.js.mystore.model.Sell
import com.js.mystore.model.ProductsList
import com.js.mystore.repository.ProductRepository
import com.js.mystore.repository.ProductSellRepository
import com.js.mystore.repository.SellRepository
import com.js.mystore.utils.DateUtils.date
import kotlinx.coroutines.launch

class SellViewModel(private val repoSell: SellRepository, private val repoProductSell: ProductSellRepository, private val repoProduct: ProductRepository) : ViewModel() {

    private var _sellLiveData = MutableLiveData<Sell>()
    val sellLiveData: LiveData<Sell> = _sellLiveData

    private var _productLiveData = MutableLiveData<List<Product>>()
    val productLiveData: LiveData<List<Product>> = _productLiveData

    private var _listSellLiveData = MutableLiveData<List<ProductsList>>()
    val listSellLiveData: LiveData<List<ProductsList>> = _listSellLiveData

    private var _sellTotalLiveData = MutableLiveData<Double>()
    val sellTotalLiveData: LiveData<Double> = _sellTotalLiveData

    private var _saleIdLiveData = MutableLiveData<Long>()
    val saleIdLiveData: LiveData<Long> = _saleIdLiveData

    fun setSale(sell: Sell) {
        viewModelScope.launch {
            repoSell.setSale(sell)
        }
    }
    // Insere dados iniciais para teste
    fun setProductSale(productSell: ProductSell) {
        viewModelScope.launch {
            repoProductSell.setProductSale(productSell)
        }
    }

    fun getProduct(filter: String) {
        viewModelScope.launch {
            _productLiveData.value = repoProduct.getProductAll(1, true).filter {
                it.name?.contains(filter, true) ?: true
            }
        }
    }

    fun setCompany(sell: Sell) {
        viewModelScope.launch {
            // repoSale.setCompany(sale)
        }
    }

    fun setItemToListSell(productId: Long, productName: String, qtd: Int, value: Double) {
        _listSellLiveData.value = _listSellLiveData.value?.plus(ProductsList(productId, productName, qtd, value)) ?: listOf(
            ProductsList(productId, productName, qtd, value)
        )

        setSellTotal(value * qtd)
    }

    fun setSale() {
        val sale = Sell(null, sellTotalLiveData.value, true, date(), date())

        viewModelScope.launch {
            _saleIdLiveData.value = repoSell.setSale(sale)
            Log.i("INSERT_ID", "Inserted ID is: $saleIdLiveData.value ")
        }
    }

    fun setSaleProduct(list: List<ProductsList>, saleId: Long) {

        viewModelScope.launch {
            list.forEach { item ->
                val product = ProductSell(item.productId, saleId, item.qtd, item.price, true, date(), date())
                repoProductSell.setProductSale(product)
                Log.e("ITEM", "$saleIdLiveData $item.toString()")
            }.also {
                _listSellLiveData.value = listOf()
                _sellTotalLiveData.value = 0.0
            }
        }
    }

    private fun setSellTotal(itemValue: Double) {
        _sellTotalLiveData.value = _sellTotalLiveData.value?.plus(itemValue) ?: itemValue
    }
}
