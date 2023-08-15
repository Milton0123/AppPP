package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityStockAdminBinding

class StockAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.stockAdminBtnBack.setOnClickListener { backAdmin() }
    }

    private fun backAdmin() {
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
    }


}
