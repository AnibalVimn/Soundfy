package pusios.com.soundfy.model

class Party(id: Int,
            private val name: String,
            private val color: String,
            subCatalog: MutableList<Catalog>,
            clips: MutableList<Clip>) : Catalog(id, subCatalog, clips)
