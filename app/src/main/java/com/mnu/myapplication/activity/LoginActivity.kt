package com.mnu.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mnu.myapplication.R
import com.mnu.myapplication.data.UserModel
import com.mnu.myapplication.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    val TAG: String = "로그"


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
                Log.d(TAG, "로그인버튼 클릭")
                val email = binding.tvEmail.text.toString()
                val password = binding.tvPassword.text.toString()
                Login(email, password)
            }
            R.id.btn_signup -> {
                Log.d(TAG, "회원가입버튼클릭")
                intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }

        }
    }


    /***
     * 기능함수
     */





    fun Login(email: String, password: String) {
        var auth = FirebaseAuth.getInstance()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        MakeToast("오늘도 파이팅입니다")
                        var user : UserModel = UserModel(email,password)
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        MakeToast("아이디 또는 비밀번호가 일치하지않습니다")
                    }
                }
        } else {
            MakeToast("이메일과 비밀번호를 모두입렵해주세요")
        }
    }



    fun MakeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}