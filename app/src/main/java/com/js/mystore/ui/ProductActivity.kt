package com.js.mystore.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            if (productName.isNotEmpty() && productDesc.isNotEmpty()) {
                productViewModel.setProduct(productName, productDesc)
                binding.txtProductName.text = null
                binding.txtProductDescription.text = null
            } else {
                Toast.makeText(this, "Favor Preencher os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buyToolbar.setNavigationOnClickListener { view ->
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
            finish()
        }
    }
}
