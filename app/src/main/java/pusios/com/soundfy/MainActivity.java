package pusios.com.soundfy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import pusios.com.soundfy.dagger.DaggerDependencies;
import pusios.com.soundfy.db.DbBuilder;
import pusios.com.soundfy.model.Catalog;

public class MainActivity extends AppCompatActivity {

    @Inject
    Observable<Catalog> observableCatalog;
    @Inject
    Gson gson;

    private Catalog catalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerDependencies.getInjector().inject(this);

        observableCatalog.subscribe(this::setCatalog);
    }

    public void setCatalog(final Catalog catalog) {
        this.catalog = catalog;
        Log.d("db", "catalog " + gson.toJson(this.catalog));
    }
}
