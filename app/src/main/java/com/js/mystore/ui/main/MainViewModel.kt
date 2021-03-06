package com.js.mystore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.mystore.model.Buy
import com.js.mystore.model.Company
import com.js.mystore.model.Sell
import com.js.mystore.model.relations.SellProducts
import com.js.mystore.repository.BuyRepository
import com.js.mystore.repository.CompanyRepository
import com.js.mystore.repository.SellRepository
import com.js.mystore.utils.DateUtils.convertStringToDate
import kotlinx.coroutines.launch

class MainViewModel(
    private val companyRepo: CompanyRepository,
    private val buyRepo: BuyRepository,
    private val sellRepo: SellRepository,
) : ViewModel() {

    private var _companyLiveData = MutableLiveData<Company>()
    val companyLiveData: LiveData<Company> get() = _companyLiveData

    private var _listBuy = MutableLiveData<List<Buy>>()
    private val listBuy: LiveData<List<Buy>> get() = _listBuy

    private var _listSell = MutableLiveData<List<Sell>>()
    private val listSell: LiveData<List<Sell>> get() = _listSell

    private var _sellTotal = MutableLiveData<Double>()
    val sellTotal: LiveData<Double> get() = _sellTotal

    private var _buyTotal = MutableLiveData<Double>()
    val buyTotal: LiveData<Double> get() = _buyTotal

    private var _buyAndSellTotal = MutableLiveData<Double>()
    val buyAndSellTotal: LiveData<Double> get() = _buyAndSellTotal

    private var _listSellProducts = MutableLiveData<List<SellProducts>>()
    val listSellProducts: LiveData<List<SellProducts>> get() = _listSellProducts

    fun getCompany(companyId: Long) {
        viewModelScope.launch {
            _companyLiveData.value = companyRepo.getCompany(companyId)
        }
    }

    fun setCompany(company: Company) {
        viewModelScope.launch {
            companyRepo.setCompany(company)
        }
    }

    fun getBuy(dateInitial: String, dateEnd: String) {

        val initial = convertStringToDate(dateInitial)
        val final = convertStringToDate(dateEnd)

        _buyTotal.value = 0.0

        viewModelScope.launch() {
            _listBuy.value = buyRepo.getBuyAll().filter {
                it.updateAt!! >= initial && it.updateAt!! <= final
            }.onEach {
                _buyTotal.value = _buyTotal.value?.plus(it.valueBuy!!) ?: it.valueBuy
            }
        }
    }

    fun getSell(dateInitial: String, dateEnd: String) {
        val initial = convertStringToDate(dateInitial)
        val final = convertStringToDate(dateEnd)

        _sellTotal.value = 0.0

        viewModelScope.launch() {
            _listSell.value = sellRepo.getSellAll().filter {
                it.updateAt!! >= initial && it.updateAt!! <= final
            }.onEach {
                _sellTotal.value = _sellTotal.value?.plus(it.valueSale!!) ?: it.valueSale
            }
        }
    }

    fun getSellProduct(dateInitial: String, dateEnd: String) {

        val initial = convertStringToDate(dateInitial)
        val final = convertStringToDate(dateEnd)

        viewModelScope.launch() {
            _listSellProducts.value = sellRepo.getSellProductsAll().sortedByDescending {
                it.sell.sellId
            }.filter {
                it.sell.updateAt!! >= initial && it.sell.updateAt!! <= final
            }
        }
    }

    fun getTotalBuyAndSell(valueBuy: Double, valueSell: Double) {
        _buyAndSellTotal.value = valueSell - valueBuy
    }
}
