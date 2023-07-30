package com.example.apppp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppp.databinding.ActivityLoginBinding

class login_activity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}