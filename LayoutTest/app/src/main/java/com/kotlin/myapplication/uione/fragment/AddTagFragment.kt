package com.kotlin.myapplication.uione.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.myapplication.R
import com.kotlin.myapplication.uione.adapter.TagAdapter
import com.kotlin.myapplication.uione.model.Tag
import com.kotlin.myapplication.uione.viewModel.TagViewModel


class AddTagFragment : DialogFragment(), TagAdapter.OnItemClickListener {
    private val viewModel by lazy { TagViewModel(requireContext()) }
    private val addButton by lazy { requireView().findViewById<Button>(R.id.btnAddlDialog) }
    private val nameEditText by lazy { requireView().findViewById<EditText>(R.id.edAddTag) }
    private val adapter by lazy { TagAdapter(this) }

    private var tagToEdit: Tag? = null
        set(value) {
            field = value
            if (value == null) {
                addButton.text = getString(R.string.add)
            } else {
                addButton.text = getString(R.string.update)
            }
        }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_tag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCancelAddTag = view.findViewById<Button>(R.id.btnCancelDialogAdd)
        btnCancelAddTag.setOnClickListener {
            dismiss()
        }
        val btnAddTag = view.findViewById<Button>(R.id.btnAddlDialog)
        btnAddTag.setOnClickListener {
            createOrUpdateTag()
        }

        nameEditText.doOnTextChanged { text, start, before, count ->
            if (nameEditText.text.isEmpty()) {
                tagToEdit = null
            }
        }
        showTags()
        setupRecyclerView()

    }
    private fun setupRecyclerView() {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun createOrUpdateTag() {
        if (tagToEdit != null) {
            onUpdate(tagToEdit!!)
            tagToEdit = null
        } else {
            createTag()
        }
    }

    private fun onUpdate(tag: Tag) {
        if (tag.isVaild()) {
            val name = nameEditText.text.toString()
            val id = tag.id

            viewModel.updateTag(name, id) {
                showTags()
            }
            nameEditText.text.clear()
            Toast.makeText(context, "update successful", Toast.LENGTH_SHORT).show()
        }

        setupRecyclerView()
    }

    @SuppressLint("CutPasteId")
    private fun createTag() {
        val tag = Tag(
            name = nameEditText.text.toString()
        )
        if (tag.isVaild()) {
            viewModel.insertTag(tag) {
                showTags()
            }
            nameEditText.text.clear()
            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "you have not entered", Toast.LENGTH_SHORT).show()
        }
        setupRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showTags() {
        viewModel.getTags() {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddTagFragment()
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    }

    override fun onDelete(tag: Tag) {
        val alertDialog = AlertDialog.Builder(activity)
        alertDialog.setTitle("notification")
        alertDialog.setMessage("you really want to delete !")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteTag(tag.id) {
                showTags()
                nameEditText.text.clear()
                addButton.text = getString(R.string.add)
                tagToEdit = null
            }
        }
        alertDialog.setNegativeButton("NO") { _, _ ->
        }
        val dialog = alertDialog.create()
        dialog.show()
    }

    @SuppressLint("CutPasteId")
    override fun onShowName(tag: Tag) {
        nameEditText.setText(tag.name)
        tagToEdit = tag
    }



}




