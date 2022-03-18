package com.js.mystore.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.js.mystore.databinding.ActivityBuyBinding
import com.js.mystore.ui.buy.BuyListAdapter
import com.js.mystore.ui.buy.BuyViewModel
import com.js.mystore.ui.buy.ProductListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuyActivity : AppCompatActivity() {
    private val buyViewModel: BuyViewModel by viewModel()

    private lateinit var _binding: ActivityBuyBinding
    private val binding get() = _binding

    var productId: Long? = null
    var qtd: Int = 0
    var value: Double = 0.0
    var prodName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buyViewModel.productLiveData.observe(this) { listProducts ->
            val listProdAdapter = ProductListAdapter(listProducts)

            binding.listProducts.adapter = listProdAdapter

            listProdAdapter.setListener(object : ProductListAdapter.OnItemClickListener {
                override fun onItemClick(product: Long, productName: String) {
                    productId = product
                    prodName = productName
                    // Toast.makeText(this@SaleActivity, "Tost $product", Toast.LENGTH_SHORT).show()
                }
            })
        }
        var productSearch = ""
        buyViewModel.getProduct(productSearch)
        binding.txtProductSearch.addTextChangedListener {
            productSearch = binding.txtProductSearch.text.toString()
            buyViewModel.getProduct(productSearch)
        }

        buyViewModel.buyIdLiveData.observe(this) { buyId ->
            buyViewModel.setSaleProduct(buyViewModel.listBuyLiveData.value!!, buyId)
        }

        buyViewModel.buyTotalLiveData.observe(this) { value ->
            binding.txtSaleTotal.text = value.toString()
        }

        // List product sell
        buyViewModel.listBuyLiveData.observe(this) { list ->

            val adapter = BuyListAdapter(list)
            binding.recyclerView.adapter = adapter
        }

        binding.btnAddSale.setOnClickListener {
            if (productId != null) {
                qtd = binding.txtQtdValue.text.toString().toInt()
                value = binding.txtPriceValue.text.toString().replace(',', '.').toDouble()
                buyViewModel.setItemToListSell(productId!!, prodName, qtd, value)
            } else {
                Toast.makeText(this@BuyActivity, "Favor Selecionar um produto", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSaleSave.setOnClickListener {
            if (buyViewModel.listBuyLiveData.value?.isNotEmpty() == true) {
                buyViewModel.setSale()
            } else {
                Toast.makeText(this@BuyActivity, "Não tem Items na lista", Toast.LENGTH_LONG).show()
            }
        }

        binding.buyToolbar.setNavigationOnClickListener { view ->
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
            finish()
        }
    }
}
