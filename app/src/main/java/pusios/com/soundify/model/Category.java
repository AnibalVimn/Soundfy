package pusios.com.soundify.model;

import java.util.List;
public abstract class Category {

    public abstract int id();

    public abstract List<Category> subCategories();

    public abstract List<Clip> clips();
}
