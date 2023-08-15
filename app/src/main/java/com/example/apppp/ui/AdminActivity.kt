package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()

    }
    fun onClicks(){
        binding.adminBtnBack.setOnClickListener { backLogin() }
        binding.adminBtnSale.setOnClickListener { goSales() }
        binding.adminBtnStock.setOnClickListener { goStock() }
        binding.adminBtnProduct.setOnClickListener { goAbm() }
    }
    private fun backLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goSales() {
        val sales = Intent(this, SaleAdminActivity::class.java)
        startActivity(sales)
    }

    private fun goStock() {
        val stock = Intent(this, StockAdminActivity::class.java)
        startActivity(stock)
    }

    private fun goAbm(){
        val user = Intent(this, AbmProductActivity::class.java)
        startActivity(user)
    }
}
