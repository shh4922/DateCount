package com.mnu.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mnu.myapplication.R
import com.mnu.myapplication.data.DataBase
import com.mnu.myapplication.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() ,View.OnClickListener{
    private lateinit var binding: ActivitySignupBinding
    private val database = DataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignupOk.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_signup_ok->{
                val email = binding.tvSignupEmail.text.toString()
                val password = binding.tvSignupPassword.text.toString()
                database.MakeUser(email,password)
            }
        }
    }

}