package com.example.apppp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityStockAdminBinding

class stock_admin_activity : AppCompatActivity() {

    private lateinit var binding: ActivityStockAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stockadminBtnBack.setOnClickListener{volver()}
    }

    fun volver(){
        val intent= Intent(this, admin_activity::class.java)
        startActivity(intent)
    }
}