package pusios.com.soundfy

import android.app.Application

import pusios.com.soundfy.dagger.DaggerDependencies

class SoundfyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerDependencies.init(this)
    }
}
