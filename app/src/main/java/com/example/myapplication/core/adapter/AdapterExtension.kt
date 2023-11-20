package com.example.myapplication.core.adapter

fun lazyAdapter(vararg viewHolders: ViewHolderProvider<*>) = lazy {
    BaseAdapter(*viewHolders)
}