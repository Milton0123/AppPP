package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apppp.databinding.ActivityLoginBinding
import com.example.apppp.back.users


class login_activity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtnRedirect.setOnClickListener{verificacion_de_campos()}

    }

    fun verificacion_de_campos(){
        if(binding.loginTextUser.text.toString().length == 0 ){
            Toast.makeText(this, "El campo de usuario no debe de estar vacío",Toast.LENGTH_SHORT).show()
        }
        if(binding.loginTextPassword.text.toString().length == 0){
            Toast.makeText(this, "El campo de contraseña no debe de estar vacío", Toast.LENGTH_SHORT).show()
        }
        if(binding.loginTextUser.text.toString() == users.empleados.name &&
            binding.loginTextPassword.text.toString() == users.empleados.password){

            val intent=Intent(this, employee_activity::class.java)
            startActivity(intent)
        }
        if(binding.loginTextUser.text.toString() == users.admin.name &&
            binding.loginTextPassword.text.toString() == users.admin.password){

            val intent2=Intent(this, admin_activity::class.java)
            startActivity(intent2)
        }
    }
}