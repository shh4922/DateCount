package com.mnu.myapplication.Contractor

import com.mnu.myapplication.data.UserModel

interface SignupConstactor {
    interface View {
        fun showToast(msg: String)

        fun moveToLogin()
    }

    interface Presenter {
        fun checkEmailPasswordIsNull(email:String,password:String)
    }
}