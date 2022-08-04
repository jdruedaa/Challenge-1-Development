import kotlin.Pair;
import java.util.ArrayList;

public class NodoArbolesJava
{
    private int id;
    private int valor;
    int[] posicion;
    int longitudMax;
    String mejorCamino;
    ArrayList<Integer> edges;
    ArrayList<ArrayList<String>> caminos;
    public NodoArbolesJava(int id, int valor, int[] posicion)
    {
        this.id = id;
        this.valor = valor;
        this.posicion = posicion;
        longitudMax = 1;
        mejorCamino = "" + valor;
        edges = new ArrayList<Integer>();
        caminos = new ArrayList<ArrayList<String>>();
    }


    public String toStringNA()
    {
        return "" + id + "," + valor+ "," + posicion + "," + longitudMax;
    }

    public int getId()
    {
        return id;
    }

    public int getValor()
    {
        return valor;
    }
}
