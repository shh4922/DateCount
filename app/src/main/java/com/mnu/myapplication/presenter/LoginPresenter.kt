package com.mnu.myapplication.presenter

import com.google.firebase.auth.FirebaseAuth
import com.mnu.myapplication.Contractor.LoginConstractor
import com.mnu.myapplication.data.UserModel

class LoginPresenter(var view: LoginConstractor.View? = null) : LoginConstractor.Presenter {


    private val auth by lazy { FirebaseAuth.getInstance() }



    override fun Login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()
        ) {
            view?.showToast("아이디와 비밀번호를 모두 입력하세요")
            return
        }
        requestLogin(email, password)
    }

    private fun requestLogin(email: String, password: String) {
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view?.showToast("로그인 완료")
                    view?.successToLogin()
                } else {
                    view?.showToast("아이디 또는 비밀번호가 틀렸습니다")
                }
            }
    }




}