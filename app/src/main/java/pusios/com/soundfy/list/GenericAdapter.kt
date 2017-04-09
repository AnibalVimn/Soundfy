package pusios.com.soundfy.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open abstract class GenericAdapter<I, L>(val items: List<I>, val listener: L):
        RecyclerView.Adapter<GenericViewHolder<I, L>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<I, L> {
        val itemView = LayoutInflater.from(parent.context).inflate(getLayout(), parent, false)
        return getViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<I, L>, position: Int) {
        holder.bindItem(getItem(position), listener)
    }

    override fun getItemCount(): Int = items.count()

    protected fun getItem(position: Int) : I = items[position]

    protected abstract fun getLayout(): Int

    protected abstract fun getViewHolder(view: View) : GenericViewHolder<I, L>
}