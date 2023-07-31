package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivitySaleAdminBinding

class sale_admin_activity : AppCompatActivity() {
    private lateinit var binding: ActivitySaleAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saleadminBtnBack.setOnClickListener{volver()}
    }

    fun volver(){
        val intent= Intent(this, admin_activity::class.java)
        startActivity(intent)
    }
}