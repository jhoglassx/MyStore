package com.js.mystore.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.js.mystore.R
import com.js.mystore.databinding.ActivityMainBinding
import com.js.mystore.model.Company
import com.js.mystore.ui.main.MainSellListAdapter
import com.js.mystore.ui.main.MainViewModel
import com.js.mystore.utils.DateUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        binding.dateInitial.setText(sharedPref.getString("dateInitial", "01/01/2022"))
        binding.dateFinal.setText(sharedPref.getString("dateEnd", "01/12/2022"))

        val dateInitial = binding.dateInitial
        val dateFinal = binding.dateFinal

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
            mainViewModel.getTotalBuyAndSell(totalBuy, totalSell)
        }

        mainViewModel.sellTotal.observe(this) {
            totalSell = it
            binding.txtSell.text = totalSell.toString().replace('.', ',')
            mainViewModel.getTotalBuyAndSell(totalBuy, totalSell)
        }

        mainViewModel.buyAndSellTotal.observe(this) {
            binding.txtTotalValue.text = it.toString().replace('.', ',')
        }

        binding.btnFiltrar.setOnClickListener {
            saveFilter(dateInitial.text.toString(), dateFinal.text.toString())
            filter(dateInitial.text.toString(), dateFinal.text.toString())
        }

        val companyMock = listOf(
            Company(1, "Primeira Empresa", true, DateUtils.date(), DateUtils.date())
        )

        companyMock.forEach { mainViewModel.setCompany(it) }

        binding.mainToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.more -> {
                    false
                }
                else -> false
            }
        }
    }

    private fun filter(dateInitial: String, dateEnd: String) {

        mainViewModel.getBuy(dateInitial, dateEnd)
        mainViewModel.getSell(dateInitial, dateEnd)
        mainViewModel.getSellProduct(dateInitial, dateEnd)
    }

    private fun saveFilter(dateInitial: String, dateEnd: String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("dateInitial", dateInitial)
            putString("dateEnd", dateEnd)
            apply()
        }
    }
}
