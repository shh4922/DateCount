package com.mnu.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mnu.myapplication.presenter.LoginPresenter
import com.mnu.myapplication.R
import com.mnu.myapplication.Contractor.LoginConstractor
import com.mnu.myapplication.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginConstractor.View {
    val TAG: String = "로그"

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)

        presenter = LoginPresenter()

    }

    override fun onStart() {
        presenter.view=this
        super.onStart()
    }

    override fun onStop() {
        presenter.view=null
        super.onStop()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                presenter?.Login(binding.tvEmail.text.toString(), binding.tvPassword.text.toString())
            }
            R.id.btn_signup -> {
                moveToSignup()
            }
        }
    }


    /***
     * 토스트메시지
     */
    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /***
     * 로그인성공시 mainActivity로 이동
     */
    override fun successToLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    /***
     * 회원가입을 누를시 SignupActivity로 이동
     */
    override fun moveToSignup() {
        startActivity(Intent(this, SignupActivity::class.java))
    }


}