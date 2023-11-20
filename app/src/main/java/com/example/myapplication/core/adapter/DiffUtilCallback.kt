package com.example.myapplication.core.adapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList: List<Any>,
    private val newList: List<Any>
) : DiffUtil.Callback() {

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        if (old is ISmartModel) {
            return old.getChangePayload(new)
        }
        if (new is ISmartModel) {
            return new.getChangePayload(old)
        }
        return old == new
    }

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        val old = oldList[p0]
        val new = newList[p1]
        if (old is ISmartModel) {
            return old.areItemsTheSame(new)
        }
        if (new is ISmartModel) {
            return new.areItemsTheSame(old)
        }
        return old.hashCode() == new.hashCode()
    }

    override fun areContentsTheSame(oldItemIndex: Int, newIndex: Int): Boolean {
        val old = oldList[oldItemIndex]
        val new = newList[newIndex]

        if (old is ISmartModel) {
            return old.isContentsTheSame(new)
        }
        if (new is ISmartModel) {
            return new.isContentsTheSame(old)
        }
        return old == new
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

}