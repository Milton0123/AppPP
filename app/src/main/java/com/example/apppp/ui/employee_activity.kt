package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityEmployeeBinding

class employee_activity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.employeeBtnBack.setOnClickListener { volver() }
        binding.employeeBtnStock.setOnClickListener { ir_stock() }
        binding.employeeBtnSale.setOnClickListener { ir_ventas() }
    }

    fun volver() {
        val back = Intent(this, login_activity::class.java)
        startActivity(back)
    }

    fun ir_ventas() {
        val ventas = Intent(this, sales_activity::class.java)
        startActivity(ventas)
    }

    fun ir_stock() {
        val stock = Intent(this, stock_activity::class.java)
        startActivity(stock)
    }
}
