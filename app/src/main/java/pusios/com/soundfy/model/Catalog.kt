package pusios.com.soundfy.model

open class Catalog(private val id: Int, val subCatalogs: List<Catalog>, val clips: List<Clip>)
