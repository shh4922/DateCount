package com.mnu.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mnu.myapplication.Presenter
import com.mnu.myapplication.R
import com.mnu.myapplication.ViewInterface
import com.mnu.myapplication.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener,ViewInterface {
    private lateinit var binding: ActivityLoginBinding
    val TAG: String = "로그"
    var presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                intent = Intent(this, MainActivity::class.java)
                presenter.Login(binding.tvEmail.text.toString(),binding.tvPassword.text.toString(),intent)
            }
            R.id.btn_signup -> {
                intent = Intent(this, SignupActivity::class.java)
                presenter.startActivity(intent)
            }
        }
    }


    /***
     * 기능함수
     */
    override fun MakeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun startActivity(intent: Intent) {
        startActivity(intent)
    }



}