package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivitySaleAdminBinding

class SaleAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaleAdminBinding
    private lateinit var viewModel: ProductViewModel
    private var repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleAdminBinding.inflate(layoutInflater)
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
        binding.saleAdminRecyclerViewList.adapter = adapter
    }
    private fun onClicks(){
        binding.saleAdminBtnBack.setOnClickListener { backAdmin() }
    }

    private fun backAdmin() {
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
        finish()
    }
}
