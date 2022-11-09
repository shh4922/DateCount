package com.mnu.myapplication.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mnu.myapplication.R
import com.mnu.myapplication.data.DateModel

import com.mnu.myapplication.databinding.FragmentDialogBinding

import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class DialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentDialogBinding
    private val binding get() = mBinding!!

    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val database by lazy { Firebase.database.reference }
    private var datemodel:DateModel?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDialogBinding.inflate(layoutInflater, fl_container, false)
        binding.btnDate.setOnClickListener(this)
        binding.btnOk.setOnClickListener(this)
        datemodel= DateModel()
        return binding.root
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_date -> {
                showDatePickerDialog()
            }
            R.id.btn_ok -> {
                btnOK()
            }
        }

    }


    /***
     * apply가 날짜를 선택했을때 현재 캘린더가 선택된날짜로 set을 한다
     */
    private fun showDatePickerDialog() {

        val calendar = Calendar.getInstance() // 처음
        val datePickerDialog = DatePickerDialog(
            requireContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            { view, year, monthOfYear, dayOfMonth ->
                try {
                    val selectedDate = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, monthOfYear)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }.time.time

                    //선택된날짜로 날짜선택버튼 UI 변경
                    binding.btnDate.text = formatDate.format(selectedDate)
                    calculateDate(selectedDate)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )

        datePickerDialog.show()
    }

    /***
     * 선택된 날짜를 받아와서
     * 현재날짜와 선택된날짜를 계산함,
     * 이후 dateModel에 해당날짜 저장
     */
    private fun calculateDate(selectedDate: Long) {
        var today = Calendar.getInstance().time.time
        var d_day = (selectedDate - today) / (24 * 60 * 60 * 1000)
        //모델에 데이터 저장
        datemodel?.date =d_day.toInt()

    }


    /***
     * 날짜와, 시험이름 입력후, 확인을누를시 수행
     * 시험이름이 null이면 return
     * 날짜 , 시험이름이 모두 저장되어있다면 saveDataBase
     */
    private fun btnOK() {
        if (binding.etName.text.isEmpty() ) {
            showToast("시험이름을 입력해주세요")
            return
        }

        saveDataBase()
    }


    /***
     * 만들어진 datemoel을 firebase realtimeDB 에 저장
     */
    private fun saveDataBase(){
        datemodel?.testname =binding.etName.text.toString()
        database.child("Test_Data").child(auth.currentUser?.uid.toString()).push().setValue(datemodel)
    }




    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}