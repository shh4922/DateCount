package com.mnu.myapplication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.mnu.myapplication.data.UserModel

class PresenterSignup (auth: FirebaseAuth):SignupConstractor.Presenter{

    private var auth = auth
    //private var view = LoginContractor.View

    override fun signup(user:UserModel){
        auth=FirebaseAuth.getInstance()
        if (user.email != null && user.password != null) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("로그", "가입성공")
                    } else {
                        Log.d("로그", "가입실패")
                    }
                }
        }
        Log.d("로그", "null값사용")
    }

}