package com.kotlin.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kotlin.myapplication.databinding.ActivityMainBinding
import com.kotlin.myapplication.uione.fragment.TestOneFragment
import com.kotlin.myapplication.uithree.TestThreeFragment
import com.kotlin.myapplication.uitwo.fragment.TestTwoFragment



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
         replaceFragment(TestOneFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.testOne -> replaceFragment(TestOneFragment())
                R.id.testTwo -> replaceFragment(TestTwoFragment())
                else -> replaceFragment(TestThreeFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentMain, fragment)
        fragmentTransition.commit()
    }

}