package pusios.com.soundfy.db;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import pusios.com.soundfy.model.Author;
import pusios.com.soundfy.model.Catalog;
import pusios.com.soundfy.model.Clip;
import pusios.com.soundfy.model.Party;

public class DbBuilder {

    private Author author;
    private Party party;
    private int catalogIdCounter;

    public String buildDb(final Gson gson) {
        return gson.toJson(createCatalog());
    }

    private Catalog createCatalog() {
        final List<Catalog> catalogs = new ArrayList<>();

        author = createAuthor("rajoy");
        author.getClips().add(new Clip("raw.rajoy.hola", "hola"));
        author.getClips().add(new Clip("raw.rajoy.adios", "adios"));

        party = createParty("pp", "blue");
        party.getSubCatalogs().add(author);
        catalogs.add(party);

        author = createAuthor("iglesias");
        author.getClips().add(new Clip("raw.iglesias.si", "si"));
        author.getClips().add(new Clip("raw.iglesias.no", "no"));

        party = createParty("podemos", "morado");
        party.getSubCatalogs().add(author);
        catalogs.add(party);

        return new Catalog(0, catalogs, new ArrayList<Clip>());
    }

    private Party createParty(final String name, final String color) {
        return new Party(++catalogIdCounter,
                         name,
                         color,
                         new ArrayList<Catalog>(),
                         new ArrayList<Clip>());
    }

    private Author createAuthor(final String name) {
        return new Author(++catalogIdCounter,
                          name,
                          new ArrayList<Catalog>(),
                          new ArrayList<Clip>());
    }
}
