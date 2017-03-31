package pusios.com.soundify.db;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import pusios.com.soundify.model.Author;
import pusios.com.soundify.model.Catalog;
import pusios.com.soundify.model.Clip;
import pusios.com.soundify.model.Party;

public class DbGenerator {

    private Author author;
    private Party party;
    private int catalogIdCounter;

    public void buildDb() {
        RuntimeTypeAdapterFactory<Catalog> adapter = RuntimeTypeAdapterFactory
                .of(Catalog.class)
                .registerSubtype(Catalog.class)
                .registerSubtype(Party.class)
                .registerSubtype(Author.class);

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter).create();
        String db = gson.toJson(createCatalog());
        Catalog catalog = gson.fromJson(db, Catalog.class);

        Log.d("db", "DB 1: " + db);
        Log.d("db", "DB 2: " + gson.toJson(catalog));
        Log.d("db", "DB 3: " + catalog.getSubCatalogs().get(0));
    }

    private Catalog createCatalog() {
        final List<Catalog> catalogs = new ArrayList<>();

        //Create author and its clips
        author = createAuthor("rajoy");
        author.getClips().add(new Clip("raw.rajoy.hola", "hola"));
        author.getClips().add(new Clip("raw.rajoy.adios", "adios"));

        party = createParty("pp", "blue");
        party.getSubCatalogs().add(author);
        catalogs.add(party);

        //Create author and its clips
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
