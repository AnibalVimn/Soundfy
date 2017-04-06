package pusios.com.soundfy.dagger

import android.app.Application

import pusios.com.soundfy.dagger.component.ApplicationComponent
import pusios.com.soundfy.dagger.component.DaggerApplicationComponent
import pusios.com.soundfy.dagger.module.ApplicationModule

/*object DaggerDependencies {

    lateinit var injector: ApplicationComponent
        private set

    fun init(application: Application) {
        injector = DaggerApplicationComponent
                .builder().applicationModule(ApplicationModule(application)).build()
    }
}*/

class DaggerDependencies {

    companion object {

        lateinit var injector: ApplicationComponent
            private set

        fun init(application: Application) {
            injector = DaggerApplicationComponent
                    .builder().applicationModule(ApplicationModule(application)).build()
        }
    }

}