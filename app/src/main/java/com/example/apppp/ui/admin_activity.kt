package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityAdminBinding

class admin_activity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adminBtnBack.setOnClickListener { volver() }
        binding.adminBtnSale.setOnClickListener { ir_ventas() }
        binding.adminBtnStock.setOnClickListener { ir_stock() }
    }

    fun volver() {
        val intent = Intent(this, login_activity::class.java)
        startActivity(intent)
    }

    fun ir_ventas() {
        val ventas = Intent(this, sale_admin_activity::class.java)
        startActivity(ventas)
    }

    fun ir_stock() {
        val stock = Intent(this, stock_admin_activity::class.java)
        startActivity(stock)
    }
}
