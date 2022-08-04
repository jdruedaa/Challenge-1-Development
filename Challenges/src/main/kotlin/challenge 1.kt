import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.Comparator
import java.util.PriorityQueue

fun main()
{
    //val In = BufferedReader(InputStreamReader(System.`in`))
    val In = File("./Docs/map.txt").bufferedReader()
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
            if(j>0)
            {
                actual.edges.add(id-1)
            }
            if(j<ancho-1)
            {
                actual.edges.add(id+1)
            }
            if(i>0)
            {
                actual.edges.add(id-ancho)
            }
            if(i<alto-1)
            {
                actual.edges.add(id+ancho)
            }
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
    println("Steepest path length: ${respuesta.first}")
    println("List of paths:")
    println(respuesta.second)
    /*Old caminos: var caminos = respuesta.third
    var caminoActual : String
    while(!caminos.isEmpty())
    {
        caminoActual = caminos.remove()
        if(caminoActual.equals(""))
        {
            break
        }
        println(caminoActual)
    }
    println("-----")
    */
    //nodos.map { println(it.toStringNA()) }
    //println("-----")
    /**i = 0
    println(nodosPriorizados.peek().toStringNA())
    while(i<200)
    {
        println(nodosPriorizados.remove().toStringNA())
        i++
    }
    */
}

/*ToDo Implementación actual solo podrá encontrar los mejores caminos para cada nodo, no todos los caminos posibles
   Para eso hay que encontrar los mejores caminos en cada dirección y guardarlos, luego borrar si se encuentra un nodo más abajo
*/
fun calculateAnswer(nodosPriorizados : PriorityQueue<NodoArboles>,  nodos : MutableList<NodoArboles>): Pair<Int, String>
        //Old caminos: Triple<Int, String, PriorityQueue<String>>
        //MutableList<String>
        //MutableList<MutableList<Pair<Int, String>>?>
{
    var nodoActual : NodoArboles
    var vecinoActual : NodoArboles
    //var caminos = mutableListOf<MutableList<Pair<Int, String>>>()
    //var caminos = mutableListOf<String>()
    //ToDo optimizar última comparación (doble recorrido de string en realidad es necesario?)
    /*Old caminos: val compararCamino : Comparator<String> = compareByDescending<String> {it.split("-").size}.thenByDescending { calculateSteepness(it) }
        .thenByDescending { it.substringBefore("-").toInt() }
    var caminos = PriorityQueue<String>(nodosPriorizados.size,compararCamino)
    */
    /*for (i in 0..nodos.size)
    {
        caminos.add("")
    }*/
    var id : Int
    var valor : Int
    var valorVecino : Int
    var edges : MutableList<Int>
    var longitudMax : Int
    var longitudMaxVecino : Int
    var longitudMaxGlobal = -1
    var mejorCamino : String
    var mejorCaminoVecino : String
    var mejorCaminoGlobal = ""
    var inclinacionMejorCamino : Int
    var inclinacionMejorCaminoVecino : Int
    var inclinacionMejorCaminoGlobal = -1
    while(!nodosPriorizados.isEmpty())
    {
        nodoActual = nodosPriorizados.remove()
        id = nodoActual.id
        valor = nodoActual.valor
        longitudMax = nodoActual.longitudMax
        edges = nodoActual.edges
        mejorCamino = nodoActual.mejorCamino
        for(edge in edges)
        {
            vecinoActual = nodos[edge]
            valorVecino = vecinoActual.valor
            longitudMaxVecino = vecinoActual.longitudMax
            mejorCaminoVecino = vecinoActual.mejorCamino
            if(valor < valorVecino)
            {
                if(longitudMaxVecino > longitudMax || longitudMax == 1)
                {
                    longitudMax = longitudMaxVecino + 1
                    nodoActual.longitudMax = longitudMax
                    mejorCamino = "$mejorCaminoVecino-$valor"
                    nodoActual.mejorCamino = mejorCamino
                }
                else if(longitudMaxVecino == longitudMax) {
                    inclinacionMejorCamino = calculateSteepness(mejorCamino)
                    inclinacionMejorCaminoVecino = calculateSteepness(mejorCaminoVecino)
                    if (inclinacionMejorCaminoVecino > inclinacionMejorCamino)
                    {
                        mejorCamino = "$mejorCaminoVecino-$valor"
                        nodoActual.mejorCamino = mejorCamino
                    }
                }
            }
        }
        //caminos[id] = mejorCamino
        if(longitudMax > longitudMaxGlobal)
        {
            longitudMaxGlobal = longitudMax
            //Old caminos: caminos.add(mejorCaminoGlobal)
            mejorCaminoGlobal = mejorCamino
            inclinacionMejorCaminoGlobal = calculateSteepness(mejorCamino)
        }
        else if(longitudMax == longitudMaxGlobal) {
            inclinacionMejorCamino = calculateSteepness(mejorCamino)
            if (inclinacionMejorCamino > inclinacionMejorCaminoGlobal)
            {
                //Old caminos: caminos.add(mejorCaminoGlobal)
                mejorCaminoGlobal = mejorCamino
                inclinacionMejorCaminoGlobal = inclinacionMejorCamino
            }
        }
        else
        {
            //Old caminos: caminos.add(mejorCamino)
        }
    }
    //ToDo quitar subcaminos
    //Old caminos: return Triple(longitudMaxGlobal, mejorCaminoGlobal, caminos)
    return Pair(longitudMaxGlobal, mejorCaminoGlobal)
}

fun calculateSteepness(camino : String) : Int
{
    //ToDo Optimizable?
    var inclinacion = -1
    if(camino != "")
    {
        //var final = camino.substringBefore("-").toInt()
        //var inicio = camino.substringAfterLast("-").toInt()
        var nums = camino.split("-").map { it.toInt() }
        inclinacion = nums[0] - nums[nums.size-1]
        //inclinacion = final - inicio
    }
    return inclinacion
}