package com.mnu.myapplication.presenter

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mnu.myapplication.Contractor.SignupConstactor
import com.mnu.myapplication.data.UserModel

class SignupPresenter(var view: SignupConstactor.View? = null) : SignupConstactor.Presenter {

    var userModel: UserModel? = null
    private val databaseReference by lazy { Firebase.database.reference }
    private val auth by lazy { FirebaseAuth.getInstance() }

    /***
     * 이메일 비밀번호 null체크 후, 확인시 가입시작
     */
    override fun checkEmailPasswordIsNull(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            view?.showToast("이메일과 비밀번호를 모두입력해 주세요")
            return
        }
        signupUser(email,password)
    }


    /***
     * 회원가입
     */
    private fun signupUser(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        SaveOnRealTimeDB(userModel)  왜넣었는지 아직 잘모르겠음..
                        view?.showToast("가입완료 되었습니다.")
                        view?.moveToLogin()
                    } else {
                        view?.showToast("이미 사용중인 계정입니다")
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun SaveOnRealTimeDB(userModel: UserModel) {
        databaseReference.child("User").push().setValue(userModel)
    }



}