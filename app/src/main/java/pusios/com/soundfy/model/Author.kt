package pusios.com.soundfy.model

class Author(id: Int,
             private val name: String,
             subCatalogs: List<Catalog>,
             clips: List<Clip>) : Catalog(id, subCatalogs, clips)
