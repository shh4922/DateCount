package com.mnu.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mnu.myapplication.R
import com.mnu.myapplication.databinding.ActivityIntroBinding
import com.mnu.myapplication.databinding.ActivitySignupBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        },3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}