package com.mnu.myapplication

import android.content.Intent

interface ViewInterface {

    fun MakeToast(msg:String)

    fun startActivity(intent: Intent)

}