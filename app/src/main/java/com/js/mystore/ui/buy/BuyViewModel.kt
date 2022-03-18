package com.js.mystore.ui.buy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.mystore.model.*
import com.js.mystore.repository.BuyRepository
import com.js.mystore.repository.ProductBuyRepository
import com.js.mystore.repository.ProductRepository
import com.js.mystore.utils.DateUtils.date
import kotlinx.coroutines.launch

class BuyViewModel(private val buyRepo: BuyRepository, private val buyProdRepo: ProductBuyRepository, private val prodRepo: ProductRepository) : ViewModel() {

    private var _saleLiveData = MutableLiveData<Sell>()
    val sellLiveData: LiveData<Sell> = _saleLiveData

    private var _productLiveData = MutableLiveData<List<Product>>()
    val productLiveData: LiveData<List<Product>> = _productLiveData

    private var _listBuyLiveData = MutableLiveData<List<ProductsList>>()
    val listBuyLiveData: LiveData<List<ProductsList>> = _listBuyLiveData

    private var _buyTotalLiveData = MutableLiveData<Double>()
    val buyTotalLiveData: LiveData<Double> = _buyTotalLiveData

    private var _buyIdLiveData = MutableLiveData<Long>()
    val buyIdLiveData: LiveData<Long> = _buyIdLiveData

    fun setBuy(buy: Buy) {
        viewModelScope.launch {
            buyRepo.setBuy(buy)
        }
    }
    // Insere dados iniciais para teste
    fun setProductBuy(productBuy: ProductBuy) {
        viewModelScope.launch {
            buyProdRepo.setProductBuy(productBuy)
        }
    }

    fun getProduct(filter: String) {
        viewModelScope.launch {
            _productLiveData.value = prodRepo.getProductAll(1, true)
                .filter {
                    it.name?.contains(filter, true) ?: true
                }
                .sortedBy {
                    it.name
                }
        }
    }

    fun setCompany(sell: Sell) {
        viewModelScope.launch {
            // repoSale.setCompany(sale)
        }
    }

    fun setItemToListSell(productId: Long, productName: String, qtd: Int, value: Double) {
        _listBuyLiveData.value = _listBuyLiveData.value?.plus(ProductsList(productId, productName, qtd, value)) ?: listOf(
            ProductsList(productId, productName, qtd, value)
        )

        setSellTotal(value * qtd)
    }

    fun setSale() {
        val buy = Buy(null, buyTotalLiveData.value, true, date(), date())

        viewModelScope.launch {
            _buyIdLiveData.value = buyRepo.setBuy(buy)
            Log.i("INSERT_ID", "Inserted ID is: $buyIdLiveData.value ")
        }
    }

    fun setSaleProduct(list: List<ProductsList>, saleId: Long) {

        viewModelScope.launch {
            list.forEach { item ->
                val product = ProductBuy(item.productId, saleId, item.qtd, item.price, true, date(), date())
                buyProdRepo.setProductBuy(product)
                Log.e("ITEM", "$buyIdLiveData $item.toString()")
            }.also {
                _listBuyLiveData.value = listOf()
                _buyTotalLiveData.value = 0.0
            }
        }
    }

    private fun setSellTotal(itemValue: Double) {
        _buyTotalLiveData.value = _buyTotalLiveData.value?.plus(itemValue) ?: itemValue
    }
}
