package pusios.com.soundfy.manager

import android.media.MediaPlayer
import android.content.res.Resources
import android.media.AudioManager


class AudioClipManager(val resources: Resources) {

    private val player: MediaPlayer

    init {
        player = MediaPlayer()
        player.setAudioStreamType(AudioManager.STREAM_MUSIC)
    }

    fun playClip(idClip: String, packageName: String) {
        if (player.isPlaying) {
            player.pause()
        }
        player.reset()
        val resId = resources.getIdentifier(idClip, "raw", packageName)
        val afd = resources.openRawResourceFd(resId)
        player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        afd.close()
        player.prepare()
        player.start()
    }
}