package pusios.com.soundfy.model

open class Catalog(private val id: Int,
                   private val subCatalogs: List<Catalog>,
                   private val clips: List<Clip>)
