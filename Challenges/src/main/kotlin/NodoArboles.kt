class NodoArboles (val id: Int,
                   val valor: Int,
                   val posicion: Pair<Int,Int>)
{
    var longitudMax = -1
    var edges = mutableListOf<NodoArboles>()

    fun toStringNA() : String
    {
        return "$id,$valor,$posicion,$longitudMax"
    }


}
