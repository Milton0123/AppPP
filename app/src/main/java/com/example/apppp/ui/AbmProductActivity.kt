package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityAbmProductBinding

class AbmProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbmProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbmProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()

    }

    private fun onClicks() {
        binding.crudProductIvBack.setOnClickListener { backActivity() }
        //binding.crudBtAdd.setOnClickListener { addProduct() }
        //binding.crudBtRemove.setOnClickListener { deleteProduct() }
    }


    private fun backActivity() {
        val back = Intent(this, AdminActivity::class.java)
        startActivity(back)
    }
}
