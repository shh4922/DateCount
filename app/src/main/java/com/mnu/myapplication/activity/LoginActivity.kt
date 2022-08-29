package com.mnu.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mnu.myapplication.R
import com.mnu.myapplication.data.DataBase
import com.mnu.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() ,View.OnClickListener{
    private lateinit var binding: ActivityLoginBinding
    val dataBase = DataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login->{
                val email = binding.tvEmail.text.toString()
                val password = binding.tvPassword.text.toString()
                dataBase.Login(v,email,password)

            }
            R.id.btn_signup->{

            }
        }
    }
}