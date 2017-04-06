package pusios.com.soundfy.dagger.component

import javax.inject.Singleton

import dagger.Component
import pusios.com.soundfy.MainActivity
import pusios.com.soundfy.dagger.module.ApplicationModule

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}
