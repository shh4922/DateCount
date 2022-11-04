package com.mnu.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mnu.myapplication.Contractor.Presenter.LoginPresenter
import com.mnu.myapplication.R
import com.mnu.myapplication.Contractor.Interface.LoginInterface
import com.mnu.myapplication.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginInterface.ViewInterface, LoginInterface.PresenterInterface {
    private lateinit var binding: ActivityLoginBinding
    val TAG: String = "로그"
    var loginPresenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)


        loginPresenter = LoginPresenter()

    }

    override fun onStart() {
        loginPresenter?.onStart(this,this)
        super.onStart()
    }

    override fun onStop() {
        loginPresenter?.onStop()
        loginPresenter = null
        super.onStop()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                loginPresenter?.Login(binding.tvEmail.text.toString(), binding.tvPassword.text.toString())
            }
            R.id.btn_signup -> {
                loginPresenter?.moveToSignup()
            }
        }
    }


    /***
     * 기능함수
     */
    override fun MakeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun successToLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun moveToSignup() {
        startActivity(Intent(this, SignupActivity::class.java))
    }


}