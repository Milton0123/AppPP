package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivityStockBinding

class StockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockBinding
    private lateinit var viewModel : ProductViewModel
    private var repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockBinding.inflate(layoutInflater)
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
        binding.stockRecyclerViewAllProducts.adapter = adapter
    }

    private fun onClicks(){
        binding.stockBtnBack.setOnClickListener { backEmployee() }
    }

    private fun backEmployee() {
        val intent = Intent(this, EmployeeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
