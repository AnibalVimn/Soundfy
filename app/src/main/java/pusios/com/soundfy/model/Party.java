package pusios.com.soundfy.model;

import java.util.List;

public class Party extends Catalog {

    private final String name;
    private final String color;

    public Party(final int id,
                 final String name,
                 final String color,
                 final List<Catalog> subCatalog,
                 final List<Clip> clips) {
        super(id, subCatalog, clips);
        this.name = name;
        this.color = color;
    }
}
