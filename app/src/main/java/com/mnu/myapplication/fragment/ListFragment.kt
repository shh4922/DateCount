package com.mnu.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnu.myapplication.adapter.TextAdapter
import com.mnu.myapplication.data.TextModel
import com.mnu.myapplication.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    // 바인딩 객체 타입에 ?를 붙여서 null을 허용 해줘야한다. ( onDestroy 될 때 완벽하게 제거를 하기위해 )
    private var mBinding: FragmentListBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentListBinding.inflate(inflater,container,false)

        val textlist = arrayListOf(
            TextModel("s"),
            TextModel("a"),
            TextModel("b")
        )
        binding.rcText.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rcText.setHasFixedSize(true)
        binding.rcText.adapter = TextAdapter(textlist)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }






}