package com.kotlin.myapplication.uione.adapterimport android.annotation.SuppressLintimport androidx.databinding.BindingAdapterimport androidx.recyclerview.widget.RecyclerViewclass RecyclerBindingAdapter {    companion object {        @SuppressLint("NotifyDataSetChanged")        @BindingAdapter(value = ["items"])        @JvmStatic        fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {            val adapter: BaseAdapter<T> = recyclerView.adapter as BaseAdapter<T>            items?.let {                adapter.list = items                adapter.notifyDataSetChanged()            }        }    }}