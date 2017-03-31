package pusios.com.soundify.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Clip implements Parcelable {

    public abstract String id();
    public abstract String title();

    public static Clip create(final String id, final String title){
        return new AutoValue_Clip(id, title);
    }
}
