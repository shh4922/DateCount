package com.mnu.myapplication.activity


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mnu.myapplication.R
import com.mnu.myapplication.databinding.ActivityDialogBinding
import kotlinx.android.synthetic.main.activity_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class DialogActivity(context: Context) : Dialog(context), View.OnClickListener {

    val TAG: String = "로그"
    val formatDate = SimpleDateFormat("yyyy MM dd", Locale.KOREAN)
    private lateinit var binding : ActivityDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDate.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_date -> {
                dateChoice()
            }
            R.id.btn_ok -> {

            }
        }

    }

    //날짜를 선택하였을때의 함수
    private fun dateChoice() {
        val getdate = Calendar.getInstance()
        //현재날짜
        val datePicker = DatePickerDialog(
            this.context,
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, i)
                    set(Calendar.MONTH, i2)
                    set(Calendar.DAY_OF_MONTH, i3)
                }.time.time
                //선택된 시간을 인자로 보내서 현재시간과의 차를 계산해주는 함수
                dateCount(selectedDate)
                val date = formatDate.format(selectedDate)
                //선택될날짜로 ui텍스트 변경
                btn_date.text = date
            },
            getdate.get(Calendar.YEAR),
            getdate.get(Calendar.MONTH),
            getdate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    //선택된 날짜를 바탕으로 현재날짜와 비교해서 날짜를 카운트해주는 함수
    private fun dateCount(selectedDate: Long) {
        val currentTime: Long = System.currentTimeMillis()
        val result = (selectedDate - currentTime) / (60 * 60 * 24 * 1000)
        Log.d(TAG, "선택한 날짜: ${formatDate.format(selectedDate)}")
        Log.d(TAG, "현재 날짜: ${formatDate.format(currentTime)}")
        Log.d(TAG, "두시간의 날자 차 : ${result.toString()}")
    }
}
