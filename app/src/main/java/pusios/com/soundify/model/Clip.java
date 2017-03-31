package pusios.com.soundify.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Clip implements Parcelable {
    public abstract String id();
    public static Clip create(final String id){
        return new AutoValue_Clip(id);
    }
}
