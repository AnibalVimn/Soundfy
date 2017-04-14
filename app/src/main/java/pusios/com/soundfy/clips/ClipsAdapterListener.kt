package pusios.com.soundfy.clips

import pusios.com.soundfy.model.Clip

interface ClipsAdapterListener {

    fun onClipClicked(clip: Clip)
}