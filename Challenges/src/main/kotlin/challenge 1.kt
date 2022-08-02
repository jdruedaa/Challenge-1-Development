import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Comparator
import java.util.PriorityQueue

fun main()
{
    val In = BufferedReader(InputStreamReader(System.`in`))
    var i = 0
    var j = 0
    val size = In.readLine()!!.split(" ").map { it.toInt() }
    val alto = size[0]
    val ancho = size[1]
    var arboles = listOf<Int>()
    var actual : NodoArboles
    var id = 0
    var valor = 0
    var posicion = Pair(0,0)
    val compararNodoPorValor : Comparator<NodoArboles> = compareByDescending {it.valor}
    var nodosPriorizados = PriorityQueue(alto*ancho, compararNodoPorValor)
    var nodos = mutableListOf<NodoArboles>()
    //var edges = mutableListOf<NodoArboles>()
    while(i<alto)
    {
        arboles = In.readLine()!!.split(" ").map { it.toInt() }
        while (j < ancho)
        {
            valor = arboles[j]
            posicion = Pair(i,j)
            actual = NodoArboles(id, valor, posicion)
            nodos.add(actual)
            if(valor >= 0)
            {
                nodosPriorizados.add(actual)
            }
            j++
            id++
        }
        i++
        j = 0
    }
    var respuesta = calculateAnswer(nodosPriorizados, nodos)
    nodos.map { println(it.toStringNA()) }
    println("-----")
    /**i = 0
    println(nodosPriorizados.peek().toStringNA())
    while(i<200)
    {
        println(nodosPriorizados.remove().toStringNA())
        i++
    }
    */
}

//Implementación actual solo podrá encontrar los mejores caminos para cada nodo, no todos los caminos posibles
fun calculateAnswer(nodosPriorizados : PriorityQueue<NodoArboles>,  nodos : MutableList<NodoArboles>): Array<MutableList<Pair<Int, String>>?> {
    var nodoActual : NodoArboles
    var vecinoActual : NodoArboles
    var caminos = arrayOfNulls<MutableList<Pair<Int, String>>>(nodos.size)
    var id : Int
    while(!nodosPriorizados.isEmpty())
    {
        nodoActual = nodosPriorizados.remove()
        id = nodoActual.id

        //if(nodos.get(id+1) != null && )
    }
    return caminos
}