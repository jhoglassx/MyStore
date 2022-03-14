package com.js.mystore.repository

import com.js.mystore.model.Company

interface CompanyRepository {
    suspend fun getCompany(companyId: Long): Company
    suspend fun setCompany(company: Company)
    suspend fun updateCompany(company: Company)
    suspend fun deleteCompany(company: Company)
}
