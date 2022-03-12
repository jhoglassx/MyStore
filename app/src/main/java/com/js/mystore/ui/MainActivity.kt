package com.js.mystore.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.js.mystore.databinding.ActivityMainBinding
import com.js.mystore.model.*
import com.js.mystore.ui.buy.BuyViewModel
import com.js.mystore.ui.main.MainViewModel
import com.js.mystore.ui.main.ProductViewModel
import com.js.mystore.ui.sell.SellViewModel
import com.js.mystore.utils.DateUtils
import com.js.mystore.utils.DateUtils.date
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val productViewModel: ProductViewModel by viewModel()
    private val buyViewModel: BuyViewModel by viewModel()
    private val sellViewModel: SellViewModel by viewModel()

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            if (binding.btnPurchase.isVisible) {
                binding.btnPurchase.visibility = View.INVISIBLE
                binding.btnSale.visibility = View.INVISIBLE
            } else {
                binding.btnPurchase.visibility = View.VISIBLE
                binding.btnSale.visibility = View.VISIBLE
            }
        }

        binding.btnSale.setOnClickListener { view ->
            val intent = Intent(view.context, SellActivity::class.java)
            view.context.startActivity(intent)
        }

        binding.btnPurchase.setOnClickListener { view ->
            val intent = Intent(view.context, BuyActivity::class.java)
            view.context.startActivity(intent)
        }

        val companyMock = listOf<Company>(
            Company(1, "Primeira Empresa", true, DateUtils.date(), DateUtils.date())
        )

        val product = listOf(
            Product(1, 1, "Camisa Azul", "Camisa azul com manga curta e desenho na frente", true, date(), date()),
            Product(2, 1, "Camisa Preta", "Camisa preta com manga curta e desenho na frente", true, date(), date()),
            Product(3, 1, "Camisa Bege", "Camisa bege com manga curta e desenho na frente e atrás", true, date(), date()),
            Product(4, 1, "Camisa Vermelha", "Camisa vermelha com manga curta e desenho na frente e atrás", true, date(), date())
        )

        val purchase = listOf(
            Buy(1, 100.00, true, date(), date()),
            Buy(2, 70.00, true, date(), date()),
            Buy(3, 50.00, true, date(), date()),
            Buy(4, 120.00, true, date(), date()),
            Buy(5, 10.00, true, date(), date())
        )

        val sale = listOf(
            Sell(1, 100.00, true, date(), date()),
            Sell(2, 50.00, true, date(), date()),
            Sell(1, 25.00, true, date(), date()),
            Sell(3, 120.00, true, date(), date()),
            Sell(4, 10.00, true, date(), date())
        )

        val productPurchase = listOf(
            ProductBuy(1, 1, 1, 99.99, true, date(), date()),
            ProductBuy(2, 2, 3, 99.99, true, date(), date()),
            ProductBuy(3, 3, 5, 99.99, true, date(), date()),
            ProductBuy(4, 4, 2, 99.99, true, date(), date()),
            ProductBuy(2, 5, 9, 99.99, true, date(), date()),
        )

        val productSale = listOf(
            ProductSell(1, 1, 10, 99.99, true, date(), date()),
            ProductSell(2, 1, 1, 99.99, true, date(), date()),
            ProductSell(3, 1, 5, 99.99, true, date(), date()),
            ProductSell(4, 2, 3, 99.99, true, date(), date()),
            ProductSell(2, 3, 7, 99.99, true, date(), date()),
        )

        companyMock.forEach { mainViewModel.setCompany(it) }
        product.forEach { productViewModel.setProduct(it) }
        purchase.forEach { buyViewModel.setBuy(it) }
        productPurchase.forEach { buyViewModel.setProductBuy(it) }
        productSale.forEach { sellViewModel.setProductSale(it) }
        sale.forEach { sellViewModel.setSale(it) }

        mainViewModel.getCompany(1)

        mainViewModel.companyLiveData.observe(this) {
            it
        }
    }
}
