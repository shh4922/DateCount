package com.mnu.myapplication.Contractor.Presenter

import com.google.firebase.auth.FirebaseAuth
import com.mnu.myapplication.data.UserModel
import com.mnu.myapplication.Contractor.Interface.LoginInterface

class LoginPresenter() {
    lateinit var userModel: UserModel
    var viewInterface : LoginInterface.ViewInterface?=null
    var presentinterface : LoginInterface.PresenterInterface? =null

    fun Login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()
        ) {
            viewInterface?.MakeToast("아이디와 비밀번호를 모두 입력해주세요")
        } else {
            userModel = UserModel(email, password)
            requestLogin(userModel)
        }

    }
    fun requestLogin(userModel:UserModel){
        var auth = FirebaseAuth.getInstance()
        auth?.signInWithEmailAndPassword(userModel.email, userModel.password)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewInterface?.MakeToast("로그인 완료")
                    presentinterface?.successToLogin()
                } else {
                    viewInterface?.MakeToast("아이디 또는 비밀번호가 틀렸습니다")
                }
            }
    }
    fun moveToSignup(){
        presentinterface?.moveToSignup()
    }

    fun onStart(viewInterface: LoginInterface.ViewInterface,presenterInterface: LoginInterface.PresenterInterface ){
        this.viewInterface=viewInterface
        this.presentinterface=presenterInterface
    }
    fun onStop(){
        this.viewInterface=null
        this.presentinterface=null
    }

}