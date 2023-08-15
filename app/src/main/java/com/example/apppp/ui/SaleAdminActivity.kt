package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivitySaleAdminBinding

class SaleAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaleAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()
    }
    private fun onClicks(){
        binding.saleAdminBtnBack.setOnClickListener { backAdmin() }
    }

    private fun backAdmin() {
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
    }
}
