package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apppp.databinding.ActivityLoginBinding
import com.example.apppp.back.Users
import com.example.apppp.back.Utils


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()
    }
    private fun onClicks(){
        //binding.loginBtnRedirect.setOnClickListener { Utils.validateFields() }
    }


}
