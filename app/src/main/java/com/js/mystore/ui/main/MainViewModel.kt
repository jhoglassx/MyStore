package com.js.mystore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.mystore.model.Company
import com.js.mystore.repository.CompanyRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CompanyRepository) : ViewModel() {

    private var _companyLiveData = MutableLiveData<Company>()
    val companyLiveData: LiveData<Company> = _companyLiveData

    fun getCompany(companyId: Int) {
        viewModelScope.launch {
            _companyLiveData.value = repository.getCompany(companyId)
        }
    }

    fun setCompany(company: Company) {
        viewModelScope.launch {
            repository.setCompany(company)
        }
    }
}
