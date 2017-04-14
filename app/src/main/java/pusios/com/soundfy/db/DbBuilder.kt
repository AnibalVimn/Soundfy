package pusios.com.soundfy.db

import com.google.gson.Gson

import java.util.ArrayList

import pusios.com.soundfy.model.Author
import pusios.com.soundfy.model.Catalog
import pusios.com.soundfy.model.Clip
import pusios.com.soundfy.model.Party

class DbBuilder {

    fun buildDb(gson: Gson): String {
        return gson.toJson(createCatalog())
    }

    private fun createCatalog(): Catalog {

        val clips = mutableListOf<Clip>()
        clips.add(Clip("bodyfat", "Body fat"))
        clips.add(Clip("hasta", "Hasta la vista"))
        clips.add(Clip("uglymf", "Ugly mf"))
        clips.add(Clip("wakeupdrunk", "Wake up"))
        clips.add(Clip("wtfgoinon", "WTF"))

        clips.add(Clip("wtfgoinon", "Fake 1"))
        clips.add(Clip("wtfgoinon", "Fake 2"))
        clips.add(Clip("wtfgoinon", "Fake 3"))
        clips.add(Clip("wtfgoinon", "Fake 4"))
        clips.add(Clip("wtfgoinon", "Fake 5"))

        return Catalog(0, mutableListOf(), clips)
    }
}
