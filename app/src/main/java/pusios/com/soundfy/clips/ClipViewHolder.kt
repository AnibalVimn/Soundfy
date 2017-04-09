package pusios.com.soundfy.clips

import android.view.View
import android.widget.Button
import pusios.com.soundfy.R
import pusios.com.soundfy.list.GenericViewHolder
import pusios.com.soundfy.model.Clip

class ClipViewHolder(view : View): GenericViewHolder<Clip, ClipsAdapterListener>(view) {

    private val button: Button

    init {
        button = view.findViewById(R.id.button_clip) as Button
    }

    override fun bindItem(item: Clip, listener: ClipsAdapterListener) {
        button.setText(item.title)
        button.setOnClickListener {listener.onClipClicked()}
    }
}