package pusios.com.soundfy.model;

import java.util.List;

public class Author extends Catalog {

    private final String name;

    public Author(final int id,
                  final String name,
                  final List<Catalog> subCatalogs,
                  final List<Clip> clips) {
        super(id, subCatalogs, clips);
        this.name = name;
    }
}
