package pusios.com.soundfy;

import android.app.Application;

import pusios.com.soundfy.dagger.DaggerDependencies;

public class SoundfyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDependencies.init(this);
    }
}
