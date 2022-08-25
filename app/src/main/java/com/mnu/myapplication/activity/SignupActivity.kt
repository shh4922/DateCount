package com.mnu.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mnu.myapplication.R
import com.mnu.myapplication.data.DataBase
import com.mnu.myapplication.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() ,View.OnClickListener{
    private lateinit var binding: ActivitySignupBinding
    val database =DataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignupOk.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_signup_ok->{
                Log.d("로그","로그인클릭")
                signup()
            }
        }
    }
    private fun signup(){
        val email = binding.tvSignupEmail.text.toString()
        val password = binding.tvSignupPassword.text.toString()
        Log.d("로그",email)
        Log.d("로그",password)
        database.MakeUser(email,password)
    }
}