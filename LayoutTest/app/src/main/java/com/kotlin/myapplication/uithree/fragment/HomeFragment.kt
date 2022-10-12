package com.kotlin.myapplication.uithree.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.myapplication.BR
import com.kotlin.myapplication.R
import com.kotlin.myapplication.databinding.FragmentHomeBinding
import com.kotlin.myapplication.uione.adapter.MenuAdapter
import com.kotlin.myapplication.uione.viewModel.MenuViewModel


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel = MenuViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        viewModel.setupMenu(requireContext())
        val adapter = MenuAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)

    }


}