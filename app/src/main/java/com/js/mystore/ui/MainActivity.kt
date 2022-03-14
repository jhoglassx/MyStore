package com.js.mystore.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.js.mystore.R
import com.js.mystore.databinding.ActivityMainBinding
import com.js.mystore.model.*
import com.js.mystore.ui.buy.BuyViewModel
import com.js.mystore.ui.main.MainSellListAdapter
import com.js.mystore.ui.main.MainViewModel
import com.js.mystore.ui.product.ProductViewModel
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

        var totalBuy = 0.0
        var totalSell = 0.0

        binding.btnAdd.setOnClickListener {
            if (binding.btnPurchase.isVisible) {
                binding.btnPurchase.visibility = View.INVISIBLE
                binding.btnSale.visibility = View.INVISIBLE
                binding.btnAddProduct.visibility = View.INVISIBLE
            } else {
                binding.btnPurchase.visibility = View.VISIBLE
                binding.btnSale.visibility = View.VISIBLE
                binding.btnAddProduct.visibility = View.VISIBLE
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

        binding.btnAddProduct.setOnClickListener { view ->
            val intent = Intent(view.context, ProductActivity::class.java)
            view.context.startActivity(intent)
        }

        mainViewModel.listSellProducts.observe(this) { list ->
            binding.recyclerViewMain.adapter = MainSellListAdapter(list)
        }

        mainViewModel.buyTotal.observe(this) {
            totalBuy = it
            binding.txtBuy.text = totalBuy.toString().replace('.', ',')
            mainViewModel.getSaldoBuyAndSell(totalBuy, totalSell)
        }

        mainViewModel.sellTotal.observe(this) {
            totalSell = it
            binding.txtSell.text = totalSell.toString().replace('.', ',')
            mainViewModel.getSaldoBuyAndSell(totalBuy, totalSell)
        }

        mainViewModel.buyAndSellTotal.observe(this) {
            binding.txtTotalValue.text = it.toString().replace('.', ',')
        }

        mainViewModel.getSellProduct()
        mainViewModel.getBuy()
        mainViewModel.getSell()

        val totalBuyAndSell = totalBuy - totalSell

        val companyMock = listOf<Company>(
            Company(1, "Primeira Empresa", true, DateUtils.date(), DateUtils.date())
        )

        val product = listOf(
            listOf("Camisa Azul", "Camisa azul com manga curta e desenho na frente"),
            listOf("Camisa Preta", "Camisa preta com manga curta e desenho na frente"),
            listOf("Camisa Bege", "Camisa bege com manga curta e desenho na frente e atrás"),
            listOf("Camisa Vermelha", "Camisa vermelha com manga curta e desenho na frente e atrás")
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
        // product.forEach { productViewModel.setProduct(it[0], it[1]) }
        purchase.forEach { buyViewModel.setBuy(it) }
        productPurchase.forEach { buyViewModel.setProductBuy(it) }
        productSale.forEach { sellViewModel.setProductSale(it) }
        sale.forEach { sellViewModel.setSale(it) }

        binding.mainToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.more -> {
                    true
                }
                else -> false
            }
        }
    }
}
