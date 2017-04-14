package pusios.com.soundfy.db

import com.google.gson.Gson

import java.util.ArrayList

import pusios.com.soundfy.model.Author
import pusios.com.soundfy.model.Catalog
import pusios.com.soundfy.model.Clip
import pusios.com.soundfy.model.Party

class DbBuilder {

    private lateinit var author: Author
    private lateinit var party: Party
    private var catalogIdCounter: Int = 0

    fun buildDb(gson: Gson): String {
        return gson.toJson(createCatalog())
    }

    private fun createCatalog(): Catalog {

        val clips = mutableListOf<Clip>()
        clips.add(Clip("bodyfat", "body fat"))
        clips.add(Clip("hasta", "hasta la vista"))
        clips.add(Clip("uglymf", "ugly mf"))
        clips.add(Clip("wakeupdrunk", "wake up"))
        clips.add(Clip("wtfgoinon", "wtf"))

        /*val catalogs = ArrayList<Catalog>()

        author = createAuthor("rajoy")
        author.clips.add(Clip("raw.rajoy.hola", "hola"))
        author.clips.add(Clip("raw.rajoy.adios", "adios"))

        party = createParty("pp", "blue")
        party.subCatalogs.add(author)
        catalogs.add(party)

        author = createAuthor("iglesias")
        author.clips.add(Clip("raw.iglesias.si", "si"))
        author.clips.add(Clip("raw.iglesias.no", "no"))

        party = createParty("podemos", "morado")
        party.subCatalogs.add(author)
        catalogs.add(party)

        return Catalog(0, catalogs, ArrayList<Clip>())*/


        return Catalog(0, mutableListOf(), clips)
    }

    private fun createParty(name: String, color: String): Party {
        return Party(++catalogIdCounter,
                name,
                color,
                ArrayList<Catalog>(),
                ArrayList<Clip>())
    }

    private fun createAuthor(name: String): Author {
        return Author(++catalogIdCounter,
                name,
                ArrayList<Catalog>(),
                ArrayList<Clip>())
    }
}
