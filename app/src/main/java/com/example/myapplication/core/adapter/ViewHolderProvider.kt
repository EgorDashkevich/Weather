package com.example.myapplication.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

interface ViewHolderProvider<T : Any> {
    val itemClass: Class<T>
    fun create(parent: ViewGroup): BaseViewHolder<T>
}

class ViewHolderProviderImpl<T : Any>(
    private val viewCreator: (parent: ViewGroup) -> View,
    private val onBind: View.(item: T) -> Unit,
    override val itemClass: Class<T>
) : ViewHolderProvider<T> {

    class ViewHolder<T>(
        view: View,
        private val onBind: View.(item: T) -> Unit
    ) : BaseViewHolder<T>(view) {
        override fun onBind(item: T) {
            itemView.onBind(item)
        }
    }

    override fun create(parent: ViewGroup): BaseViewHolder<T> {
        return ViewHolder(viewCreator(parent), onBind)
    }
}

inline fun <reified T : Any> viewHolder(
    @LayoutRes layoutId: Int,
    noinline onBind: View.(item: T) -> Unit
): ViewHolderProvider<T> = ViewHolderProviderImpl(
    viewCreator = { LayoutInflater.from(it.context).inflate(layoutId, it, false) },
    onBind = onBind,
    itemClass = T::class.java
)

inline fun <reified T : Any> viewHolder(
    noinline viewCreator: (parent: ViewGroup) -> View,
    noinline onBind: View.(item: T) -> Unit
): ViewHolderProvider<T> = ViewHolderProviderImpl(
    viewCreator = viewCreator,
    onBind = onBind,
    itemClass = T::class.java
)