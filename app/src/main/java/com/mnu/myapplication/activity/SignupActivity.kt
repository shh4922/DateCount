package com.mnu.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.mnu.myapplication.PresenterSignup
import com.mnu.myapplication.R
import com.mnu.myapplication.SignupConstractor
import com.mnu.myapplication.data.DataBase
import com.mnu.myapplication.data.UserModel
import com.mnu.myapplication.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() ,View.OnClickListener,SignupConstractor.View,SignupConstractor.Presenter{
    private lateinit var binding: ActivitySignupBinding
    val auth = FirebaseAuth.getInstance()
    val presenter =PresenterSignup(auth)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignupOk.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_signup_ok->{
                signup("email","password")
            }
        }
    }

    override fun showMessage(msg: String) {

    }

    override fun signup(email: String, password: String) {

        val email = binding.tvSignupEmail.text.toString()
        val password = binding.tvSignupPassword.text.toString()
        val user =UserModel(email,password)
        presenter.signup(user)
    }
}