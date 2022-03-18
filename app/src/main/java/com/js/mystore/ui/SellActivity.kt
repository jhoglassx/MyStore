package com.js.mystore.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.js.mystore.databinding.ActivitySellBinding
import com.js.mystore.ui.sell.ProductListAdapter
import com.js.mystore.ui.sell.SellListAdapter
import com.js.mystore.ui.sell.SellViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SellActivity : AppCompatActivity() {
    private val sellViewModel: SellViewModel by viewModel()

    private lateinit var _binding: ActivitySellBinding
    private val binding get() = _binding

    var productId: Long? = null
    private var qtd: Int = 0
    private var value: Double = 0.0
    var prodName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var productSearch = ""

        sellViewModel.getProduct(productSearch)
        binding.txtProductSearch.addTextChangedListener {
            productSearch = binding.txtProductSearch.text.toString()
            sellViewModel.getProduct(productSearch)
        }

        sellViewModel.productLiveData.observe(this) { listProducts ->
            val listProdAdapter = ProductListAdapter(listProducts)

            binding.listProducts.adapter = listProdAdapter

            listProdAdapter.setListener(object : ProductListAdapter.OnItemClickListener {
                override fun onItemClick(product: Long, productName: String) {
                    productId = product
                    prodName = productName
                    Toast.makeText(this@SellActivity, prodName, Toast.LENGTH_SHORT).show()
                }
            })
        }

        sellViewModel.saleIdLiveData.observe(this) { saleId ->
            sellViewModel.setSaleProduct(sellViewModel.listSellLiveData.value!!, saleId)
        }

        sellViewModel.sellTotalLiveData.observe(this) { value ->
            binding.txtSaleTotal.text = value.toString()
        }

        // List product sell
        sellViewModel.listSellLiveData.observe(this) { list ->

            val adapter = SellListAdapter(list)
            binding.recyclerView.adapter = adapter
        }

        binding.btnAddSale.setOnClickListener {
            if (productId != null) {
                qtd = binding.txtQtdValue.text.toString().toInt()
                value = binding.txtPriceValue.text.toString().replace(',', '.').toDouble()
                sellViewModel.setItemToListSell(productId!!, prodName, qtd, value)
            } else {
                Toast.makeText(this@SellActivity, "Favor Selecionar um produto", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSaleSave.setOnClickListener {
            if (sellViewModel.listSellLiveData.value?.isNotEmpty() == true) {
                sellViewModel.setSale()
            } else {
                Toast.makeText(this@SellActivity, "Não tem Items na lista", Toast.LENGTH_LONG).show()
            }
        }

        binding.sellToolbar.setNavigationOnClickListener { view ->
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
            finish()
        }
    }
}
