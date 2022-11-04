package com.mnu.myapplication.fragment

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mnu.myapplication.R
import com.mnu.myapplication.activity.DialogActivity
import com.mnu.myapplication.adapter.DateAdapter
import com.mnu.myapplication.adapter.TextAdapter
import com.mnu.myapplication.data.DateModel
import com.mnu.myapplication.data.TextData
import com.mnu.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() ,View.OnClickListener {
    private val TAG: String = "로그"
    private var mBinding: FragmentHomeBinding? = null

    val auth = FirebaseAuth.getInstance()

    //하루한번 i를 ++해주면서 다음명언전송을 위해 count해주는상수
    var i = 1

    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    private lateinit var recyclerAdapter: DateAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var datemodel : ArrayList<DateModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d("로그", "홈화면생성1")
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.rcDate.layoutManager =LinearLayoutManager(context)
        Log.d(TAG, "1")
        binding.rcDate.setHasFixedSize(true)
        binding.rcDate.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        Log.d(TAG, "2")
        datemodel = arrayListOf<DateModel>()
        Log.d(TAG, "3")
        getDateData()
        //DateCount()

        OnBtnInit()

        return binding.root
    }

    private fun getDateData() {
        Log.d(TAG, "4 진입")
        //경로는 Date_Data에 있는 자기자신계정의 UID로
        databaseReference = FirebaseDatabase.getInstance().getReference("Date_Data").child(auth.currentUser?.uid.toString())
        Log.d(TAG+" 현재 로그인한 사람은 : ", auth.currentUser?.uid.toString())
        databaseReference.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (dateSnapshot in  snapshot.children){
                        Log.d(TAG,"데이터불러옴")
                        Log.d(TAG,dateSnapshot.)
                        //val currentdata = dateSnapshot.getValue(DateModel::class.java)
                        val currentdata = dateSnapshot.getValue<DateModel>()
                        //!!이 뭐임 대체
                        datemodel.add(currentdata!!)
                    }
                    Log.d(TAG,"데이터 다 저장함")
                    binding.rcDate.adapter = DateAdapter(datemodel)

                    Log.d(TAG,"데이터 다 불러서 보여준거임")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG,"ㅂㅅ 실패함 ㅋㅋ")
            }

        })
    }


    /***
     * 클릭 이벤트 처리
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_plus -> {
                Log.d(TAG, "+btn click")
                val dialog = DialogActivity(v.context)
                dialog.show()

            }
        }

    }


    /***
     * 왜있는지 모르겠어
     * 존재이유가 뭘까
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("로그", "홈화면생성2")

    }

    /***
     * 화면 파괴시
     */
    override fun onDestroyView() {
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroyView()
        Log.d("로그", "홈화면파괴")
    }


    /***
     * 버튼 초기화
     */
    fun OnBtnInit() {
        binding.btnPlus.setOnClickListener(this)
    }


    /***
     * 특정시간에 명언 전송 & 팝업알림 호출
     */
    fun addAlarm(){
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 1)
        cal.set(Calendar.MINUTE, 30)
        cal.set(Calendar.SECOND, 0)
    }


    /***
     * d-day 를 매일 카운트하기위해 선택한 날짜와, 현재날짜를 비교해서 다르면 계산하여 그값을 ui에 뿌려줘야할듯
     * 1. DialogActivity에서 시험날자를 선택함., 선택된 날짜는 firebase에 저장됨
     * 2. 선택한 날짜와 현재날짜를 비교하여 그 차를 UI에 뿌려줌.
     * 3. onCreate에 이 함수를 추가하여, 화면이 지워졌다가 생성될때마다 비교하여 그것을 뿌려줌.
     */
    fun DateCount(){
        //현재시간.ver1
        val currentTime: Long = System.currentTimeMillis()
        //현재시간.ver2
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time

        datemodel.clear()
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    var data_2 =  DateModel(null,null,null)
                    data_2.testname = data.child(auth.uid.toString()).getValue(String.javaClass).toString()

                    //datemodel.add(sValue)
                    Log.e("Listeners", "ValueEventListener-onDataChange : ${data.value}", )
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        }
        var email_uid = auth.currentUser?.uid.toString()
        //val databaseReference = Firebase.database.reference.child("Date_Data").child(email_uid).child("date")

    }





}