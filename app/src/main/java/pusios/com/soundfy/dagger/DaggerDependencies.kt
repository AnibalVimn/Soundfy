package pusios.com.soundfy.dagger

import android.app.Application

import pusios.com.soundfy.dagger.component.ApplicationComponent
import pusios.com.soundfy.dagger.component.DaggerApplicationComponent
import pusios.com.soundfy.dagger.module.ApplicationModule

object DaggerDependencies {

    var injector: ApplicationComponent? = null

    fun init(application: Application) {
        injector = DaggerApplicationComponent
                .builder().applicationModule(ApplicationModule(application)).build()
    }
}
