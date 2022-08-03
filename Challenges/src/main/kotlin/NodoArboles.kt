class NodoArboles (val id: Int,
                   val valor: Int,
                   val posicion: Pair<Int,Int>)
{
    var longitudMax = 1
    var mejorCamino = "$valor"
    var edges = mutableListOf<Int>()
    var caminos = mutableListOf<MutableList<String>>()

    fun toStringNA() : String
    {
        return "$id,$valor,$posicion,$longitudMax"
    }


}
