package com.mnu.myapplication.data

import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class DataBase {
    private var auth = FirebaseAuth.getInstance()
    val TAG: String = "로그"


    /**
     * 입력한 이메일과 비밀번호로 회원가입하는 함수
     * */
    fun MakeUser(email: String?, password: String?) {

        if (email != null && password != null) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "가입성공")
                    } else {
                        Log.d(TAG, "가입실패")
                    }
                }
        } else {
            Log.d(TAG, "빈값")
        }
    }


    /***
     *
     */
    fun Login(view: View, email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        MakeToast(view,"오늘도 파이팅입니다")
                    } else {

                    }
                }
        }else{

            return
        }
    }

    fun MakeToast(view:View,msg:String){
        Toast.makeText(view.context, msg, Toast.LENGTH_SHORT).show()
    }



}
