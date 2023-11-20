package com.example.myapplication.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    constructor(parent: ViewGroup, @LayoutRes idLayout: Int) : this(
        LayoutInflater.from(parent.context).inflate(idLayout, parent, false)
    )

    abstract fun onBind(item: T)

}