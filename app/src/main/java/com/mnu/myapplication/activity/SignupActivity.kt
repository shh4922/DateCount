package com.mnu.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mnu.myapplication.R
import com.mnu.myapplication.data.UserModel
import com.mnu.myapplication.databinding.ActivitySignupBinding
import java.lang.Exception

class SignupActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseReference: DatabaseReference
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignupOk.setOnClickListener(this)
        Log.d(TAG, "회원가입페이지 온")

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_signup_ok -> {

                val email = binding.tvSignupEmail.text.toString()
                val password = binding.tvSignupPassword.text.toString()
                MakeUser(email, password)

            }
        }
    }

    fun MakeUser(email: String, password: String) {

        //아이디 비밀번호가 비어있을시,
        if (email == "" || password == "") {
            MakeToast("아이디 와 비밀번호를 모두 입력해주세요")
            return
        }

        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    SaveOnRealTimeDB(email)
                    MakeToast("가입되셨습니다")
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    try {
                        task.getResult()
                    }catch (e : Exception){
                        e.printStackTrace()
                        Log.d(TAG+"에러",e.toString())
                    }
                }
            }

    }

    fun MakeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun SaveOnRealTimeDB(email:String){
        val user = UserModel(email,"password")
        databaseReference = Firebase.database.reference
        databaseReference.child("User").push().setValue(user)
    }
}