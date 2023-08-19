package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: ProductViewModel
    private var repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        checkChangesField()
        observers()
        onClicks()
    }

    private fun initViewModel() {
        val myViewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, myViewModelFactory)[(ProductViewModel::class.java)]
    }

    private fun onClicks() {
        binding.loginBtnRedirect.setOnClickListener {
            viewModel.checkUsers(
                binding.loginTextUser.text.toString().trim(),
                binding.loginTextPassword.text.toString().trim()
            )
        }

    }

    private fun observers() {
        viewModel.fieldData.observe(this) {
            binding.loginBtnRedirect.isEnabled = it
        }
        viewModel.userData.observe(this) {
            if (it) {
                goToNextScreen(binding.loginTextUser.text.toString())
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkChangesField() {
        binding.loginTextUser.doAfterTextChanged {
            viewModel.validateButton(
                it.toString(),
                binding.loginTextPassword.text.toString()
            )
        }
        binding.loginTextPassword.doAfterTextChanged {
            viewModel.validateButton(
                binding.loginTextUser.text.toString(),
                it.toString()
            )
        }
    }
    fun goToNextScreen(screen : String) {
        when (screen) {
            "admin" -> {
                startActivity(Intent(this, AdminActivity::class.java))
                finish()
            }
            "employee" -> {
                startActivity(Intent(this, EmployeeActivity::class.java))
                finish()
            }
            else -> {
                Toast.makeText(this, "User not valid", Toast.LENGTH_SHORT).show()
            }

        }
    }

}

