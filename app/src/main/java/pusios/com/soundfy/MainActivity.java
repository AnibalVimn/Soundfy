package pusios.com.soundfy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import pusios.com.soundfy.dagger.DaggerDependencies;
import pusios.com.soundfy.db.DbBuilder;
import pusios.com.soundfy.model.Catalog;

public class MainActivity extends AppCompatActivity {

    @Inject
    Catalog catalog;

    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerDependencies.getInjector().inject(this);

        Log.d("db", "catalog " + gson.toJson(catalog));
    }
}
