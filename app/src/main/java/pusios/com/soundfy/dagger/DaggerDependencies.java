package pusios.com.soundfy.dagger;

import android.app.Application;

import pusios.com.soundfy.dagger.component.ApplicationComponent;
import pusios.com.soundfy.dagger.component.DaggerApplicationComponent;
import pusios.com.soundfy.dagger.module.ApplicationModule;

public class DaggerDependencies {

    private static ApplicationComponent applicationComponent;

    public static void init(final Application application) {
        applicationComponent = DaggerApplicationComponent
                .builder().applicationModule(new ApplicationModule (application)).build();
    }

    public static ApplicationComponent getInjector() {
        return applicationComponent;
    }
}
