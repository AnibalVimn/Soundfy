package pusios.com.soundfy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pusios.com.soundfy.clips.ClipsAdapter
import pusios.com.soundfy.clips.ClipsAdapterListener
import pusios.com.soundfy.dagger.DaggerDependencies
import pusios.com.soundfy.model.Catalog

class MainActivity : AppCompatActivity(), ClipsAdapterListener {

    @Inject
    lateinit var observableCatalog: Observable<Catalog>

    lateinit var catalog: Catalog

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

    override fun onClipClicked() {
        Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}
