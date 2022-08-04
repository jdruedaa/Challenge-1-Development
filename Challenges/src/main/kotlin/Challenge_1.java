import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Challenge_1
{
    public static void main(String[] args)
    {
        Challenge_1 c1 = new Challenge_1();
        c1.lecturaYRespuesta();
    }

    public void lecturaYRespuesta()
    {
        //val In = BufferedReader(InputStreamReader(System.`in`))
        //ToDo add selección de opción: solo mejor camino o mejor camino por nodo
        try
        {
            BufferedReader entrada = new BufferedReader(new FileReader("./Docs/map.txt"));
            int i = 0;
            int j = 0;
            int[] size = Arrays.stream(entrada.readLine().split(" ")).mapToInt(it -> Integer.parseInt(it)).toArray();
            int alto = size[0];
            int ancho = size[1];
            int[] arboles = new int[ancho];
            NodoArbolesJava actual = null;
            int id = 0;
            int valor = 0;
            int[] posicion = new int[2];
            Comparator compararNodoPorValor = Comparator.comparingInt((NodoArbolesJava nodo) -> (nodo.getValor())).reversed();
            //Comparator compararNodoPorValor = Comparator<NodoArbolesJava> = compareByDescending {it.valor};
            PriorityQueue nodosPriorizados = new PriorityQueue(alto*ancho, compararNodoPorValor);
            ArrayList<NodoArbolesJava> nodos = new ArrayList();
            //var edges = mutableListOf<NodoArboles>()
            while(i<alto)
            {
                arboles = Arrays.stream(entrada.readLine().split(" ")).mapToInt(it -> Integer.parseInt(it)).toArray();
                while (j < ancho)
                {
                    valor = arboles[j];
                    posicion[0] = i;
                    posicion[1] = j;
                    actual = new NodoArbolesJava(id, valor, posicion);
                    if(j>0)
                    {
                        actual.edges.add(id-1);
                    }
                    if(j<ancho-1)
                    {
                        actual.edges.add(id+1);
                    }
                    if(i>0)
                    {
                        actual.edges.add(id-ancho);
                    }
                    if(i<alto-1)
                    {
                        actual.edges.add(id+ancho);
                    }
                    nodos.add(actual);
                    if(valor >= 0)
                    {
                        nodosPriorizados.add(actual);
                    }
                    j++;
                    id++;
                }
                i++;
                j = 0;
            }
            String[] respuesta = calculateAnswer(nodosPriorizados, nodos);
            System.out.println("Steepest path length: " + respuesta[0]);
            System.out.println("List of paths:");
            System.out.println(respuesta[1]);
            //ToDo escribir a archivo en caso de pedir caminos
            /*List caminos: i = 1
            var caminos = respuesta.third
            var caminoActual : String
            while(i < caminos.size)
            {
                caminoActual = caminos[i]
                if(caminoActual.equals(""))
                {
                    break
                }
                println(caminoActual)
                i++
            }*/


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
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public String[] calculateAnswer(PriorityQueue<NodoArbolesJava> nodosPriorizados, ArrayList<NodoArbolesJava> nodos)
    {
        NodoArbolesJava nodoActual;
        NodoArbolesJava vecinoActual;
        int id;
        int valor;
        int valorVecino;
        ArrayList<Integer> edges;
        int longitudMax;
        int longitudMaxVecino;
        int longitudMaxGlobal = -1;
        String mejorCamino;
        String mejorCaminoVecino;
        String mejorCaminoGlobal = "";
        int inclinacionMejorCamino;
        int inclinacionMejorCaminoVecino;
        int inclinacionMejorCaminoGlobal = -1;
        while(!nodosPriorizados.isEmpty())
        {
            nodoActual = nodosPriorizados.remove();
            id = nodoActual.getId();
            valor = nodoActual.getValor();
            longitudMax = nodoActual.longitudMax;
            edges = nodoActual.edges;
            mejorCamino = nodoActual.mejorCamino;
            for(int edge: edges)
            {
                vecinoActual = nodos.get(edge);
                valorVecino = vecinoActual.getValor();
                longitudMaxVecino = vecinoActual.longitudMax;
                mejorCaminoVecino = vecinoActual.mejorCamino;
                if(valor < valorVecino)
                {
                    if(longitudMaxVecino > longitudMax || longitudMax == 1)
                    {
                        longitudMax = longitudMaxVecino + 1;
                        nodoActual.longitudMax = longitudMax;
                        mejorCamino = "" + mejorCaminoVecino + "-" + valor;
                        nodoActual.mejorCamino = mejorCamino;
                    }
                    else if(longitudMaxVecino == longitudMax) {
                        inclinacionMejorCamino = calculateSteepness(mejorCamino);
                        inclinacionMejorCaminoVecino = calculateSteepness(mejorCaminoVecino);
                        if (inclinacionMejorCaminoVecino > inclinacionMejorCamino);
                        {
                            mejorCamino = "" + mejorCaminoVecino + "-" + valor;
                            nodoActual.mejorCamino = mejorCamino;
                        }
                    }
                }
            }
            //caminos[id] = mejorCamino
            if(longitudMax > longitudMaxGlobal)
            {
                longitudMaxGlobal = longitudMax;
                mejorCaminoGlobal = mejorCamino;
                inclinacionMejorCaminoGlobal = calculateSteepness(mejorCamino);
            }
            else if(longitudMax == longitudMaxGlobal) {
                inclinacionMejorCamino = calculateSteepness(mejorCamino);
                if (inclinacionMejorCamino > inclinacionMejorCaminoGlobal)
                {
                    mejorCaminoGlobal = mejorCamino;
                    inclinacionMejorCaminoGlobal = inclinacionMejorCamino;
                }
            }
            else
            {
            }
        }
        String[] respuesta = {"" + longitudMaxGlobal, mejorCaminoGlobal};
        return respuesta;
    }

    public int calculateSteepness(String camino)
    {
        //ToDo Optimizable?
        var inclinacion = -1;
        if(camino != "")
        {
            //var final = camino.substringBefore("-").toInt()
            //var inicio = camino.substringAfterLast("-").toInt()
            int[] nums = Arrays.stream(camino.split("-")).mapToInt(it -> Integer.parseInt(it)).toArray();
            inclinacion = nums[0] - nums[nums.length-1];
            //inclinacion = final - inicio
        }
            return inclinacion;
    }

}
