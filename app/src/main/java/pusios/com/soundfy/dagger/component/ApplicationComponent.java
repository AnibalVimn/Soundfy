package pusios.com.soundfy.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import pusios.com.soundfy.MainActivity;
import pusios.com.soundfy.dagger.module.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);
}
