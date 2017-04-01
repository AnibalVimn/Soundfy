package pusios.com.soundfy.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pusios.com.soundfy.R;
import pusios.com.soundfy.db.DbBuilder;
import pusios.com.soundfy.db.RuntimeTypeAdapterFactory;
import pusios.com.soundfy.model.Author;
import pusios.com.soundfy.model.Catalog;
import pusios.com.soundfy.model.Party;

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(final Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources(final Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(final Context context, final Resources resources) {
        return context.getSharedPreferences(resources.getString(R.string.app_name),
                                            Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        RuntimeTypeAdapterFactory<Catalog> adapter = RuntimeTypeAdapterFactory
                .of(Catalog.class)
                .registerSubtype(Catalog.class)
                .registerSubtype(Party.class)
                .registerSubtype(Author.class);
        return new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter).create();
    }

    @Provides
    @Singleton
    Catalog provideCatalog(final Gson gson, final SharedPreferences sharedPreferences) {
        String db = sharedPreferences.getString("db", "");
        if (TextUtils.isEmpty(db)) {
            db = new DbBuilder().buildDb(gson);
            sharedPreferences.edit().putString("db", db).commit();
        }
        return gson.fromJson(db, Catalog.class);
    }
}
