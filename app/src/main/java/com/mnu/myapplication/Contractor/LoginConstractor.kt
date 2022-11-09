package com.mnu.myapplication.Contractor

import android.content.Intent
import com.mnu.myapplication.data.UserModel

interface LoginConstractor {

    interface View{
        //메시지 전달
        fun showToast(msg:String)

        fun successToLogin()

        fun moveToSignup()

    }
    interface Presenter{
        fun Login(email:String,password:String)
    }

}