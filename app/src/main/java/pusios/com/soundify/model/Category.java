package pusios.com.soundify.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Category implements Parcelable {
    public abstract String id();
    public static Category create(final String id){
        return new AutoValue_Category(id);
    }
}
