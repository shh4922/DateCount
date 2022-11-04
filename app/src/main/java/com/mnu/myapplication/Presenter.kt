package com.mnu.myapplication

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.mnu.myapplication.activity.MainActivity
import com.mnu.myapplication.data.UserModel
import com.mnu.myapplication.databinding.ActivityLoginBinding

class Presenter(var viewInterface: ViewInterface) {
    lateinit var userModel: UserModel

    fun Login(email: String, password: String, intent: Intent) {
        if (email.isEmpty() || password.isEmpty()
        ) {
            viewInterface.MakeToast("아이디와 비밀번호를 모두 입력해주세요")

        } else {
            userModel = UserModel(email, password)
            var auth = FirebaseAuth.getInstance()
            auth?.signInWithEmailAndPassword(userModel.email, userModel.password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewInterface.MakeToast("로그인 완료")
                        viewInterface.startActivity(intent)
                    } else {
                        viewInterface.MakeToast("아이디 또는 비밀번호가 틀렸습니다")
                    }
                }
        }

    }

    fun startActivity(intent: Intent) {
        viewInterface.startActivity(intent)
    }


}