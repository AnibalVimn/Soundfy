package pusios.com.soundfy

import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pusios.com.soundfy.clips.ClipsAdapter
import pusios.com.soundfy.clips.ClipsAdapterListener
import pusios.com.soundfy.dagger.DaggerDependencies
import pusios.com.soundfy.model.Catalog
import pusios.com.soundfy.model.Clip

class MainActivity : AppCompatActivity(), ClipsAdapterListener {

    @Inject
    lateinit var observableCatalog: Observable<Catalog>

    lateinit var catalog: Catalog
    lateinit var mediaPlayer: MediaPlayer

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
        mediaPlayer = MediaPlayer.create(this,
                this.resources.getIdentifier(clip.id, "raw", this.packageName))
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}
