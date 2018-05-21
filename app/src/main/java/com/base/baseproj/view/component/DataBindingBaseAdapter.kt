package com.base.baseproj.view.component

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class DataBindingBaseAdapter: RecyclerView.Adapter<DataBindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBindingViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        var dataBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return DataBindingViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder?, position: Int) {
        val obj = getObjForPosition(position)
        holder?.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    abstract fun getObjForPosition(position: Int): Any

    abstract fun getLayoutIdForPosition(position: Int): Int

}