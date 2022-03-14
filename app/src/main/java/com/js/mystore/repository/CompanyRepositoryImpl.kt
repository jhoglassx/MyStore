package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.Company

class CompanyRepositoryImpl(private val storeDao: StoreDao) : CompanyRepository {
    override suspend fun getCompany(companyId: Long): Company {
        return storeDao.getCompany(companyId)
    }

    override suspend fun setCompany(company: Company) {
        return storeDao.insertCompany(company)
    }

    override suspend fun updateCompany(company: Company) {
        return storeDao.updateCompany(company)
    }

    override suspend fun deleteCompany(company: Company) {
        return storeDao.updateCompany(company)
    }
}
