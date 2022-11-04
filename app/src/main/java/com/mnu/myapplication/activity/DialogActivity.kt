package com.mnu.myapplication.activity


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mnu.myapplication.R
import com.mnu.myapplication.data.DateModel
import com.mnu.myapplication.databinding.ActivityDialogBinding
import kotlinx.android.synthetic.main.activity_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class DialogActivity(context: Context) : Dialog(context), View.OnClickListener {

    val TAG: String = "로그"
    val formatDate = SimpleDateFormat("yyyy MM dd", Locale.KOREAN)
    private lateinit var binding : ActivityDialogBinding
    val auth = FirebaseAuth.getInstance()
    /***
     * 선택한 날자와 현재날짜의 차를 저장할 변수 이개맞나싶다..
     */
    lateinit var result : Calendar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDate.setOnClickListener(this)
        binding.btnOk.setOnClickListener(this)


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_date -> {
                Log.d(TAG,"btn_date 실행")
                result=dateChoice()
            }
            R.id.btn_ok -> {
                Log.d(TAG,"btnOK 실행")
                btnOK(result)
            }
        }

    }

    //날짜를 선택하였을때의 함수
    private fun dateChoice(): Calendar {
        val getdate = Calendar.getInstance()
        //현재날짜
        val datePicker = DatePickerDialog(this.context, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, y)
                    set(Calendar.MONTH, m)
                    set(Calendar.DAY_OF_MONTH, d)
                }.time.time

                //선택된 시간을 인자로 보내서 현재시간과의 차를 계산해주는 함수
//                val currentTime: Long = System.currentTimeMillis()
//                val formatcurrent = formatDate.format(currentTime)


                //result = (selectedDate - currentTime) / (60 * 60 * 24 * 1000)
                val date = formatDate.format(selectedDate)

                //선택될날짜로 ui텍스트 변경
                btn_date.text = date
            },
            getdate.get(Calendar.YEAR),
            getdate.get(Calendar.MONTH),
            getdate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
        return getdate
    }




    private fun btnOK(date: Calendar){
        var email_uid = auth.currentUser?.uid.toString()
        var email = auth.currentUser?.email.toString()
        val datemodel :DateModel = DateModel(email,date,binding.etName.text.toString())
        val databaseReference = Firebase.database.reference.child("Date_Data")
        databaseReference.child(email_uid).push().setValue(datemodel)
        Log.d(TAG, "실행4")
    }


}
