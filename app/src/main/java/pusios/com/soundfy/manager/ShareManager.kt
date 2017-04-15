package pusios.com.soundfy.manager

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class ShareManager(val context: Context, val resources: Resources) {

    private val clipName = "sound.mp3"

    fun shareAudio(id: String): Observable<Uri> {
        return Observable
                .fromCallable { buildFile(id) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun buildFile(id: String): Uri {
        val dest = Environment.getExternalStorageDirectory()
        val fileId = this.resources.getIdentifier(id, "raw", context.packageName)
        val inS = context.resources.openRawResource(fileId)

        try {
            val outS = FileOutputStream(File(dest, clipName))
            val buf = ByteArray(1024)
            var len = inS.read(buf, 0, buf.size)
            while (len != -1) {
                outS.write(buf, 0, len)
                len = inS.read(buf, 0, buf.size)
            }
            inS.close()
            outS.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return createUri()
    }

    private fun createUri(): Uri = FileProvider.getUriForFile(context,
            "pusios.com.soundfy",
            File(Environment.getExternalStorageDirectory(), clipName))
}