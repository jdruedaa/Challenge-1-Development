import java.io.BufferedReader
import java.io.InputStreamReader

fun main()
{
    val In = BufferedReader(InputStreamReader(System.`in`))
    var i = 0
    var j = 0
    val size = In.readLine()!!.split(" ").map { it.toInt() }
    val alto = size[0]
    val ancho = size[1]
    var arboles = listOf<Int>()
    var actual = 0
    var nodos = mutableListOf<Int>()
    while(i<alto)
    {
        arboles = In.readLine()!!.split(" ").map { it.toInt() }
        while (j < ancho)
        {
            actual = arboles[j]
            nodos.add(actual)
            j++
        }
        i++
        j = 0
    }
    println(nodos)
}

main()