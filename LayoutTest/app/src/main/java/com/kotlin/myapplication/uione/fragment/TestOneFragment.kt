package com.kotlin.myapplication.uione.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.myapplication.BR
import com.kotlin.myapplication.R
import com.kotlin.myapplication.databinding.FragmentTestOneBinding
import com.kotlin.myapplication.uione.adapter.MenuAdapter
import com.kotlin.myapplication.uione.model.Menu
import com.kotlin.myapplication.uione.viewModel.MenuViewModel


class TestOneFragment : Fragment() {
    private val viewModel = MenuViewModel()
    lateinit var binding: FragmentTestOneBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_one, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        setupRecyclerView()
        onMenu(requireContext())
        openAddTagFragment()
        return binding.root
    }

    private fun setupRecyclerView() {
        viewModel.setupMenu(requireContext())

        val adapter = MenuAdapter(object : MenuAdapter.onClickItem {
            override fun onClick(item: Menu) {
                Toast.makeText(
                    requireContext(),
                    "name a: " + item.image.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                testInterfacePopupmenu()
            }

            fun testInterfacePopupmenu() {
                val popUpMenu = PopupMenu(context, binding.testInterface)
                popUpMenu.menuInflater.inflate(R.menu.menu, popUpMenu.menu)
                popUpMenu.setOnMenuItemClickListener { menuItem ->
                    val id = menuItem.itemId
                    when (id) {
                        R.id.menu_coppy -> binding.testInterface.text = "copy"
                        R.id.menu_sare -> binding.testInterface.text = "sare"
                        R.id.menu_delete -> binding.testInterface.text = "delete"
                        R.id.menu_save -> binding.testInterface.text = "save"
                    }
                    false
                }
                popUpMenu.show()
            }
        })

        binding.recyclerViewMenu.adapter = adapter
        binding.recyclerViewMenu.layoutManager = GridLayoutManager(requireContext(), 5)

    }

    private fun onMenu(context: Context) {
        //mennu
        val popUpMenu = PopupMenu(context, binding.categoryView.llShow)

        popUpMenu.menuInflater.inflate(R.menu.menu, popUpMenu.menu)
        popUpMenu.setOnMenuItemClickListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                R.id.menu_coppy -> binding.categoryView.tvCategory.text = "copy"
                R.id.menu_sare -> binding.categoryView.tvCategory.text = "sare"
                R.id.menu_delete -> binding.categoryView.tvCategory.text = "delete"
                R.id.menu_save -> binding.categoryView.tvCategory.text = "save"

            }

            false
        }
        binding.categoryView.llShow.setOnClickListener {
            popUpMenu.show()
        }
    }

    private fun openAddTagFragment() {
        binding.tagView.llAddNewTag.setOnClickListener {
            val dialog = AddTagFragment.newInstance()
            dialog.show(childFragmentManager, "")
        }
    }


}




