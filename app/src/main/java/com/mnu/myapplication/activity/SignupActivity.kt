package com.mnu.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.mnu.myapplication.Contractor.SignupConstactor
import com.mnu.myapplication.presenter.SignupPresenter
import com.mnu.myapplication.R
import com.mnu.myapplication.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity(), View.OnClickListener,SignupConstactor.View {

    val TAG: String = "로그"
    private lateinit var binding: ActivitySignupBinding
    private lateinit var presenter : SignupPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignupOk.setOnClickListener(this)

        presenter= SignupPresenter()

    }

    override fun onStart() {
        presenter.view =this
        super.onStart()
    }

    override fun onStop() {
        presenter.view=null
        super.onStop()
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_signup_ok -> {
                presenter?.checkEmailPasswordIsNull(binding.tvSignupEmail.text.toString(), binding.tvSignupPassword.text.toString())
            }
        }
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun moveToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
    }




}