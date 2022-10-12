package com.kotlin.myapplication.uione.adapterimport android.content.Contextimport android.view.LayoutInflaterimport android.view.ViewGroupimport android.widget.Toastimport androidx.recyclerview.widget.RecyclerViewimport com.kotlin.myapplication.BRimport com.kotlin.myapplication.databinding.ItemMenuBindingimport com.kotlin.myapplication.uione.model.Menuimport com.kotlin.myapplication.uione.model.Tagclass MenuAdapter(private val listerner : onClickItem? = null) : BaseAdapter<Menu>(ArrayList()){    interface onClickItem {        fun onClick(item: Menu)    }    inner class MyViewModel(private val binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root){        fun bind(item: Menu){            binding.ivMenu.setOnClickListener {                listerner?.onClick(item)            }            binding.setVariable(BR.menu, item)            binding.executePendingBindings()        }            }    override fun onCreateViewHolderBase(        parent: ViewGroup?,        viewType: Int    ): RecyclerView.ViewHolder {        val inflater = LayoutInflater.from(parent?.context)        val listItemBinding = ItemMenuBinding.inflate(inflater,parent,false)        return MyViewModel(listItemBinding)    }    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int) {        (holder as? MyViewModel)?.apply {            bind(list[position])        }    }}