package pusios.com.soundfy.model

class Party(id: Int,
            private val name: String,
            private val color: String,
            subCatalog: List<Catalog>,
            clips: List<Clip>) : Catalog(id, subCatalog, clips)
