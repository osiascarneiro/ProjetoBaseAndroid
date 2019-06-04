package br.com.baseproject.view.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingBaseAdapter: RecyclerView.Adapter<DataBindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        var dataBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return DataBindingViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    abstract fun getObjForPosition(position: Int): Any

    abstract fun getLayoutIdForPosition(position: Int): Int

}
