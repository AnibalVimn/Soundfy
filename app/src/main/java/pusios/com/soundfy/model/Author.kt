package pusios.com.soundfy.model

class Author(id: Int,
             private val name: String,
             subCatalogs: MutableList<Catalog>,
             clips: MutableList<Clip>) : Catalog(id, subCatalogs, clips)
