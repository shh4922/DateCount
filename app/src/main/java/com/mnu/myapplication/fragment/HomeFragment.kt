package com.mnu.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mnu.myapplication.R
import com.mnu.myapplication.adapter.DateAdapter
import com.mnu.myapplication.data.DateModel
import com.mnu.myapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), View.OnClickListener {
    private val TAG: String = "로그"
    private var mBinding: FragmentHomeBinding? = null
    private val binding get() = mBinding!!
    private var dataList : ArrayList<DateModel>? = null


//    FirebaseDatabase.getInstance()
    private val database by lazy { Firebase.database }
    private val auth by lazy { FirebaseAuth.getInstance() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnPlus.setOnClickListener(this)

        dataList = arrayListOf()
        dataList!!.add(DateModel("1", 1))
        dataList!!.add(DateModel("1", 1))
        dataList!!.add(DateModel("1", 1))

        binding.rcDate.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcDate.setHasFixedSize(true)

        binding.rcDate.adapter = DateAdapter(dataList!!)
        binding.rcDate.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


        return binding.root
    }


    /***
     * 왜있는지 모르겠어
     * 존재이유가 뭘까
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    /***
     * 클릭 이벤트 처리
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_plus -> {
                DialogFragment().show(childFragmentManager,"g")
            }
        }

    }


    /***
     * 화면 파괴시
     */
    override fun onDestroyView() {
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroyView()

    }

    override fun onStart() {
        getDBonFirebase()
        super.onStart()
    }

    private fun getDBonFirebase(){
        var reference = database.reference.child("Test_Data").child(auth.currentUser?.uid.toString())

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }


}
