package com.kotlin.myapplication.uithree

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.myapplication.R
import com.kotlin.myapplication.databinding.FragmentTestOneBinding
import com.kotlin.myapplication.databinding.FragmentTestThreeBinding
import com.kotlin.myapplication.uithree.adapter.PagerAdapter


class TestThreeFragment : Fragment() {
    lateinit var binding: FragmentTestThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_three, container, false)

        tabLayout()

        return binding.root

    }

    private fun tabLayout() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    tab.setIcon(R.drawable.ic_baseline_home_24)
                    "Home"
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_baseline_mail_24)
                    "Profile"
                }
                2 -> {
                    tab.setIcon(R.drawable.ic_baseline_settings_24)
                    "Setting"
                }
                else -> {
                    throw Resources.NotFoundException("Position Not Found")
                }
            }


        }.attach()
    } 

}
