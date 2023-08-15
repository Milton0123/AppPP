package com.example.apppp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.ProductRepository
import com.example.apppp.back.ProductViewModel
import com.example.apppp.back.ProductViewModelFactory
import com.example.apppp.back.Users
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
                binding.loginTextUser.text.toString(),
                binding.loginTextPassword.text.toString()
            )
        }

    }

    private fun observers() {
        viewModel.fieldData.observe(this) {
            binding.loginBtnRedirect.isEnabled = it
        }
        viewModel.userData.observe(this) {
            if (it) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
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

}

