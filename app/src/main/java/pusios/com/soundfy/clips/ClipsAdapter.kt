package pusios.com.soundfy.clips

import android.view.View
import pusios.com.soundfy.R
import pusios.com.soundfy.list.GenericAdapter
import pusios.com.soundfy.list.GenericViewHolder
import pusios.com.soundfy.model.Clip

class ClipsAdapter(clips: List<Clip>, listener: ClipsAdapterListener):
        GenericAdapter<Clip, ClipsAdapterListener>(clips, listener) {

    override fun getLayout(): Int {
        return R.layout.clip_row
    }

    override fun getViewHolder(view: View): GenericViewHolder<Clip, ClipsAdapterListener> {
        return ClipViewHolder(view)
    }
}