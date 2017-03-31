package pusios.com.soundify.model;

import java.util.List;

public class Catalog {

    private final int id;
    private final List<Catalog> subCatalogs;
    private final List<Clip> clips;

    public Catalog(final int id, final List<Catalog> subCatalogs, final List<Clip> clips) {
        this.id = id;
        this.subCatalogs = subCatalogs;
        this.clips = clips;
    }

    public List<Catalog> getSubCatalogs() {
        return subCatalogs;
    }

    public List<Clip> getClips() {
        return clips;
    }
}
