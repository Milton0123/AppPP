package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivityAbmProductBinding

class AbmProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbmProductBinding
    private lateinit var viewModel: ProductViewModel
    private var repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbmProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()

        onClicks()
    }
    private fun initViewModel(){
        val viewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[(ProductViewModel::class.java)]
        initRecyclerView(viewModel.getListProducts())
    }
    private fun initRecyclerView(list : MutableList<ProductData>){
        val adapter = ProductAdapter(list)
        binding.crudRvProduct.adapter = adapter
    }
    private fun onClicks() {
        binding.crudProductIvBack.setOnClickListener { backActivity() }
        //binding.crudBtAdd.setOnClickListener { addProduct() }
        //binding.crudBtRemove.setOnClickListener { deleteProduct() }
    }


    private fun backActivity() {
        val back = Intent(this, AdminActivity::class.java)
        startActivity(back)
        finish()
    }
}
