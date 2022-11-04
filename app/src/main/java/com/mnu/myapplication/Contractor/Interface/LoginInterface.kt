package com.mnu.myapplication.Contractor.Interface

import android.content.Intent
import com.mnu.myapplication.data.UserModel

interface LoginInterface {

    interface ViewInterface{
        //메시지 전달
        fun MakeToast(msg:String)


    }
    interface PresenterInterface{
        //성공시 main으로 화면전환
        fun successToLogin()

        fun moveToSignup()
    }

}