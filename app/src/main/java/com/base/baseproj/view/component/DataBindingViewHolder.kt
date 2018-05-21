package com.base.baseproj.view.component

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.base.baseproj.BR

class DataBindingViewHolder(val itemBinding: ViewDataBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: Any) {
        itemBinding.setVariable(BR.data, data)
        itemBinding.executePendingBindings()
    }

}