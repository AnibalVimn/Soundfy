package pusios.com.soundify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pusios.com.soundify.db.DbGenerator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DbGenerator().buildDb();
    }
}
