package com.mnu.myapplication

interface LoginContractor {
    interface View{

        /***
         * 유저에게 로그인 성공,실패에 관한 메시지(알림) 출력을위함
         */
        fun showMessage(msg: String)

    }
    interface Presenter{
        /***
         * 이메일과 비밀번호를 받아 로그인을 하기위한 함수
         */
        fun loginAction(email:String, password:String)

    }
}