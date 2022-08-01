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
    var id = 1
    var valor = 0
    var posicion = Pair(0,0)
    val compararNodoPorValor : Comparator<NodoArboles> = compareByDescending {it.valor}
    var nodosPriorizados = PriorityQueue(alto*ancho, compararNodoPorValor)
    var nodos = mutableListOf<NodoArboles>()
    var edges = mutableListOf<NodoArboles>()
    while(i<alto)
    {
        arboles = In.readLine()!!.split(" ").map { it.toInt() }
        while (j < ancho)
        {
            posicion = Pair(i,j)
            valor = arboles[j].toInt()
            actual = NodoArboles(id, valor, posicion)
            nodos.add(actual)
            nodosPriorizados.add(actual)
            j++
            id++
        }
        i++
        j = 0
    }
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