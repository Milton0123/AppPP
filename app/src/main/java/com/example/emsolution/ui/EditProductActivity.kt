package com.example.emsolution.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.emsolution.R
import com.example.emsolution.databinding.ActivityEditProductBinding
import com.example.emsolution.ui.crudstock.CrudStockFragment

class EditProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
    }

    fun onClick() {
        binding.editProductIvBack.setOnClickListener() {
            val intent = Intent(this, HomeAdminActivity::class.java)
            intent.putExtra("fragmentLoad", "crud_stock")
            startActivity(intent)
            finish()
        }
    }
}