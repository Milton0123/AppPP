package com.example.emsolution.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.emsolution.databinding.ActivityLoginBinding
import com.example.emsolution.ui.HomeAdminActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        initViewModel()
        checkChangesField()
        observers()
        onClick()
    }

    private fun initViewModel() {
        viewModel = LoginViewModelFactory().create(LoginViewModel::class.java)
    }

    private fun checkChangesField() {
        binding.loginTextEmail.doAfterTextChanged {
            viewModel.validateButton(
                it.toString(),
                binding.loginTextPassword.text.toString()
            )
        }
        binding.loginTextPassword.doAfterTextChanged {
            viewModel.validateButton(
                binding.loginTextEmail.text.toString(),
                it.toString()
            )
        }
    }

    private fun observers() {
        viewModel.fieldData.observe(this) {
            binding.loginBtnLogIn.isEnabled = it
            binding.loginBtnCheckIn.isEnabled = it
        }
    }

    private fun onClick() {
        binding.loginBtnCheckIn.setOnClickListener {
            hideKeyboard()
            showLoading()
            register(
                binding.loginTextEmail.text.toString(),
                binding.loginTextPassword.text.toString()
            )
        }
        binding.loginBtnLogIn.setOnClickListener {
            hideKeyboard()
            showLoading()
            login(
                binding.loginTextEmail.text.toString(),
                binding.loginTextPassword.text.toString()
            )
        }
    }

    private fun register(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "El usuario se registró exitosamente", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, HomeAdminActivity::class.java))
                    hideLoading()
                } else {
                    Toast.makeText(
                        this,
                        "message: ${task.exception?.message.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    hideLoading()
                }
            }
        auth.signOut()
    }

    private fun login(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "El usuario inició sesión exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    hideLoading()
                    val user = auth.currentUser
                    startActivity(Intent(this, HomeAdminActivity::class.java))
                } else {
                    Toast.makeText(
                        this,
                        "message: ${task.exception?.message.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    hideLoading()
                }
            }
        auth.signOut()
    }

    private fun showLoading() {
        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        val progressBar = binding.progressBar
        progressBar.visibility = View.GONE
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus
        inputMethodManager.hideSoftInputFromWindow(currentFocusView?.windowToken, 0)
    }

}
