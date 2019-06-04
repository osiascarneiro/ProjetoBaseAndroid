package br.com.baseproject.view.component

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import br.com.baseproject.BR

class DataBindingViewHolder(val itemBinding: ViewDataBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: Any) {
        itemBinding.setVariable(BR.data, data)
        itemBinding.executePendingBindings()
    }

}