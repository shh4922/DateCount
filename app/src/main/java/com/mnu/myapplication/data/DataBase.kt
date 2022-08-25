package com.mnu.myapplication.data

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class DataBase {
    private lateinit var auth: FirebaseAuth

    /**
     * 입력한 이메일과 비밀번호로 회원가입하는 함수
     * */
    fun MakeUser(email: String?, password: String?) {
        auth=FirebaseAuth.getInstance()
        Log.d("로그", "일단함수진입성공")
        if (email != null && password != null) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("로그", "가입성공")
                    } else {
                        Log.d("로그", "가입실패")
                    }
                }
        }
        Log.d("로그", "null값사용")
        return
    }

    fun Login(){

    }

    /**
     * 선택한 날자와 시험명을 사용하여 d-day를 만드는 함수
     * */
    fun MakeDate(){

    }

    
}