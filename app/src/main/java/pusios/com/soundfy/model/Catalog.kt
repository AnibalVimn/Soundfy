package pusios.com.soundfy.model

open class Catalog(private val id: Int, val subCatalogs: MutableList<Catalog>, val clips: MutableList<Clip>)
