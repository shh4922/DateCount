package com.mnu.myapplication.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnu.myapplication.R
import com.mnu.myapplication.activity.PlusActivity
import com.mnu.myapplication.adapter.TextAdapter
import com.mnu.myapplication.data.TextModel
import com.mnu.myapplication.databinding.ActivityPlusBinding
import com.mnu.myapplication.databinding.FragmentHomeBinding
import com.mnu.myapplication.databinding.FragmentListBinding

class HomeFragment : Fragment() ,View.OnClickListener{

    private var mBinding: FragmentHomeBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d("로그","홈화면생성1")
        mBinding = FragmentHomeBinding.inflate(inflater,container,false)
        val textlist = arrayListOf(
            TextModel("s"),
            TextModel("a"),
            TextModel("b")
        )
        binding.btnPlus.setOnClickListener(this)

        return binding.root
    }


    override fun onClick(v:View?){
        when(v?.id){
            R.id.btn_plus ->{
                Log.d("로그", "온클릭실행함")
                val dialog =PlusActivity(v.context)
                dialog.show()


            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("로그","홈화면생성2")

    }


    // 프래그먼트가 destroy (파괴) 될때..
    override fun onDestroyView() {
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroyView()
        Log.d("로그","홈화면파괴")
    }







}