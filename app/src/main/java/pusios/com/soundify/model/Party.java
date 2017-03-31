package pusios.com.soundify.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Party extends Category implements Parcelable {

    public abstract String name();

    public abstract String color();

    public static Party create(final int id,
                               final String name,
                               final String color,
                               final List<Category> categories,
                               final List<Clip> clips) {
        return new AutoValue_Party(id, categories, clips, name, color);
    }
}
