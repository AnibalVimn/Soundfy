package pusios.com.soundfy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ShareCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pusios.com.soundfy.clips.ClipsAdapter
import pusios.com.soundfy.clips.ClipsAdapterListener
import pusios.com.soundfy.dagger.DaggerDependencies
import pusios.com.soundfy.manager.AudioClipManager
import pusios.com.soundfy.manager.ShareManager
import pusios.com.soundfy.model.Catalog
import pusios.com.soundfy.model.Clip


class MainActivity : AppCompatActivity(), ClipsAdapterListener {

    private val rightsRequestCode = 2909

    @Inject
    lateinit var observableCatalog: Observable<Catalog>
    @Inject
    lateinit var shareManager: ShareManager
    @Inject
    lateinit var audioManager: AudioClipManager

    lateinit var catalog: Catalog
    lateinit var lastClip: Clip

    private var subscriptions: CompositeDisposable = CompositeDisposable();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerDependencies.injector.inject(this)
        subscriptions.add(observableCatalog.subscribe{
            catalog = it
            setUpClipsList()
        })
    }

    private fun setUpClipsList() {
        val clipsList: RecyclerView = findViewById(R.id.clips) as RecyclerView
        clipsList.layoutManager = LinearLayoutManager(this)
        clipsList.adapter = ClipsAdapter(catalog.clips, this)
    }

    override fun onClipClicked(clip: Clip) {
        lastClip = clip
        audioManager.playClip(clip.id, packageName)
    }

    override fun onShareClip(clip: Clip) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !==
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission
                        .WRITE_EXTERNAL_STORAGE), rightsRequestCode)
                return
            }
        }
        shareAudio(clip.id)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == rightsRequestCode
                && grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            shareAudio(lastClip.id)
        }
    }

    private fun shareAudio(clipId: String) {
        subscriptions.add(shareManager.shareAudio(clipId).subscribe(this::startShareIntent))
    }

    private fun startShareIntent(uri: Uri) {
        val intent = ShareCompat.IntentBuilder.from(this)
                .setType("audio/*")
                .setSubject("Subject")
                .setStream(uri)
                .setChooserTitle("title")
                .createChooserIntent()
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent)
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}
