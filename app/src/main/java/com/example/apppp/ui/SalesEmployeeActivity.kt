package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivitySalesBinding

class  SalesEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalesBinding
    private lateinit var viewModel: ProductViewModel
    private var repository = ProductRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesBinding.inflate(layoutInflater)
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
        binding.saleRecyclerViewList.adapter = adapter
    }

    private fun onClicks(){
        binding.saleBtnBack.setOnClickListener { backEmployee() }

    }
    private fun backEmployee() {
        val intent = Intent(this, EmployeeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
