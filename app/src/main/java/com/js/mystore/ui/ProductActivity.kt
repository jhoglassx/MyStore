package com.js.mystore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.js.mystore.databinding.ActivityProductBinding
import com.js.mystore.ui.product.ProductAdapter
import com.js.mystore.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModel()

    private lateinit var _binding: ActivityProductBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProductBinding.inflate(layoutInflater)

        setContentView(binding.root)

        productViewModel.productListLiveData.observe(this) { list ->
            binding.recyclerViewProduct.adapter = ProductAdapter(list)
        }

        productViewModel.getProductAll()

        binding.btnAddProduct.setOnClickListener {
            val productName = binding.txtProductName.text.toString()
            val productDesc = binding.txtProductDescription.text.toString()
            productViewModel.setProduct(productName, productDesc)
        }
    }
}
