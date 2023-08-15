package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityEmployeeBinding

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()
    }
    private fun onClicks(){
        binding.employeeBtnBack.setOnClickListener { backLogin() }
        binding.employeeBtnStock.setOnClickListener { goStock() }
        binding.employeeBtnSale.setOnClickListener { goSales() }
    }

    private fun backLogin() {
        val back = Intent(this, LoginActivity::class.java)
        startActivity(back)
    }

    private fun goSales() {
        val sales = Intent(this, SalesEmployeeActivity::class.java)
        startActivity(sales)
    }

    private fun goStock() {
        val stock = Intent(this, StockActivity::class.java)
        startActivity(stock)
    }
}

