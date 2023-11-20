package com.example.myapplication.core.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

open class BaseAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var generatedId: Int = 0
        get() = field++

    protected val data = ArrayList<Any>()
    private val mCellInfoMap = Hashtable<Class<out Any>, CellInfo>()

    fun registerItem(
        objectClass: Class<out Any>,
        builder: (parent: ViewGroup) -> BaseViewHolder<*>
    ): Int {

        val viewId = generatedId

        val generator = BindInterface { parent -> builder.invoke(parent) }

        val cellInfo = CellInfo(generator, viewId)
        mCellInfoMap[objectClass] = cellInfo

        return viewId
    }

    inline fun <reified T : Any> registerItem(
        noinline builder: (parent: ViewGroup) -> BaseViewHolder<T>
    ): Int = registerItem(T::class.java, builder)

    fun <T : Any> registerItem(
        wrapper: ViewHolderProvider<T>
    ) = registerItem(wrapper.itemClass) {
        wrapper.create(it)
    }

    fun interface BindInterface {
        fun createViewHolder(parent: ViewGroup): BaseViewHolder<*>
    }

    class CellInfo(
        val bindInterface: BindInterface,
        val layoutId: Int
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        val cellInfo = mCellInfoMap.entries.find { it -> it.value.layoutId == viewType }

        if (cellInfo != null) {
            return cellInfo.value.bindInterface.createViewHolder(parent)
        } else {
            return object : BaseViewHolder<Any>(TextView(parent.context)) {
                override fun onBind(item: Any) {
                    (itemView as TextView).text = "VIEW NOT EXIST"
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        (holder as BaseViewHolder<Any>).onBind(item)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = getItem(position)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                (holder as BaseViewHolder<Any>).onBind(item)
            }
        }
    }

    override fun getItemViewType(position: Int) = getCellInfo(data[position])?.layoutId ?: -1

    fun getItem(position: Int) = data[position]

    private fun getCellInfo(someModel: Any): CellInfo? {
        for (entry in mCellInfoMap.entries) {
            if (entry.key.isInstance(someModel))
                return entry.value
        }
        return null
    }

    open fun updateItems(newList: List<Any>) {
        val diffCallback = DiffUtilCallback(data, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        operator fun invoke(register: BaseAdapter.() -> Unit) = object : BaseAdapter() {
            init {
                register()
            }
        }

        operator fun invoke(vararg viewHolders: ViewHolderProvider<*>) = invoke {
            viewHolders.forEach { registerItem(it) }
        }
    }

}