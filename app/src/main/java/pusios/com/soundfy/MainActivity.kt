package pusios.com.soundfy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.gson.Gson

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pusios.com.soundfy.dagger.DaggerDependencies
import pusios.com.soundfy.model.Catalog

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var observableCatalog: Observable<Catalog>
    @Inject
    lateinit var gson: Gson

    private var catalog: Catalog? = null
    private var subscriptions: CompositeDisposable = CompositeDisposable();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerDependencies.getInjector().inject(this)
        subscriptions.add(observableCatalog.subscribe(this::setCatalog))

        /*subscriptions.add(observableCatalog.subscribeWith(object : DisposableObserver<Catalog>() {
            override fun onComplete() {
            }

            override fun onError(e: Throwable?) {
            }

            override fun onNext(value: Catalog?) {
            }
        }));*/
    }

    private fun setCatalog(catalog: Catalog) {
        this.catalog = catalog
        Log.d("db", "catalog " + gson.toJson(this.catalog))
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}
