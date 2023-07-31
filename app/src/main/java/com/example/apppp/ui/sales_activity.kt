package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivitySalesBinding

class sales_activity : AppCompatActivity() {

    private lateinit var binding: ActivitySalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saleBtnBack.setOnClickListener{volver()}
    }

    fun volver(){
        val intent= Intent(this, employee_activity::class.java)
        startActivity(intent)
    }
}