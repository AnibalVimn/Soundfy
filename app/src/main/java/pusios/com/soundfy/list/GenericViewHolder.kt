package pusios.com.soundfy.list

import android.support.v7.widget.RecyclerView
import android.view.View

open abstract class GenericViewHolder<I, L>(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bindItem(item: I, listener: L)
}