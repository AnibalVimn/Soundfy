package pusios.com.soundfy.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.text.TextUtils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.util.concurrent.Callable

import javax.inject.Singleton

import dagger.Lazy
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableFromCallable
import io.reactivex.schedulers.Schedulers
import pusios.com.soundfy.R
import pusios.com.soundfy.db.DbBuilder
import pusios.com.soundfy.db.RuntimeTypeAdapterFactory
import pusios.com.soundfy.manager.AudioClipManager
import pusios.com.soundfy.manager.ShareManager
import pusios.com.soundfy.model.Author
import pusios.com.soundfy.model.Catalog
import pusios.com.soundfy.model.Party

@Module
class ApplicationModule(internal var application: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideResources(context: Context): Resources = context.resources

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context, resources: Resources):
            SharedPreferences = context.getSharedPreferences(resources.getString(R.string.app_name),
                Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val adapter = RuntimeTypeAdapterFactory
                .of(Catalog::class.java)
                .registerSubtype(Catalog::class.java)
                .registerSubtype(Party::class.java)
                .registerSubtype(Author::class.java)
        return GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter).create()
    }

    @Provides
    @Singleton
    internal fun provideCatalog(gson: Gson, sharedPreferences: SharedPreferences): Catalog {
        var db: String = sharedPreferences.getString("db", "")
        if (TextUtils.isEmpty(db)) {
            db = DbBuilder().buildDb(gson)
            sharedPreferences.edit().putString("db", db).commit()
        }
        return gson.fromJson(db, Catalog::class.java)
    }

    @Provides
    @Singleton
    internal fun provideObservableCatalog(catalog: Lazy<Catalog>): Observable<Catalog> =
            Observable.fromCallable{ catalog.get() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())

    @Provides
    @Singleton
    internal fun provideShareManager(context: Context, resources: Resources): ShareManager
            = ShareManager(context, resources)

    @Provides
    @Singleton
    internal fun provideAudioManager(resources: Resources): AudioClipManager = AudioClipManager(resources)
}
