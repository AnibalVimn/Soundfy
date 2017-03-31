package pusios.com.soundify.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Author extends Category implements Parcelable {

    public abstract String name();

    public static Author create(final int id,
                                final String name,
                                final List<Category> categories,
                                final List<Clip> clips) {
        return new AutoValue_Author(id, categories, clips, name);
    }
}
