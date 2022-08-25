package com.mnu.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mnu.myapplication.R
import com.mnu.myapplication.databinding.ActivityMainBinding
import com.mnu.myapplication.fragment.HomeFragment
import com.mnu.myapplication.fragment.ListFragment
import com.mnu.myapplication.fragment.SettingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainPage()

    }

    fun initMainPage(){
        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = binding.bnvMain
            //findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    // 다른 프래그먼트 화면으로 이동하는 기능
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeFragment).commit()
                }
                R.id.list -> {
                    val listFragment = ListFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, listFragment).commit()
                }
                R.id.setting -> {
                    val settingFragment = SettingFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, settingFragment).commit()
                }
            }
            true
        }
            selectedItemId = R.id.home
        }
    }
}