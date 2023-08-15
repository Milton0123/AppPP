package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivityStockAdminBinding

class StockAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockAdminBinding
    private lateinit var viewModel : ProductViewModel
    private var repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()
        initViewModel()
    }

    private fun initViewModel(){
        val viewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[(ProductViewModel::class.java)]
        initRecyclerView(viewModel.getListProducts())
    }

    private fun initRecyclerView(list : MutableList<ProductData>){
        val adapter = ProductAdapter(list)
        binding.stockAdminRecyclerViewAllProducts.adapter = adapter
    }


    private fun onClicks(){
      binding.stockAdminBtnBack.setOnClickListener { backAdmin() }
    }

    private fun backAdmin() {
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
        finish()
    }


}
