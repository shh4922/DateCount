package com.mnu.myapplication

import com.mnu.myapplication.data.UserModel

interface SignupConstractor {
    interface View{
        fun showMessage(msg:String)
    }
    interface Presenter{
        fun signup(user:UserModel)
    }
}