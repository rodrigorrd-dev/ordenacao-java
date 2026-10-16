package orden.util;

public class Texto {
    
    /** 
     * @return String
     */
    public String introducao(){
        return "\n\n\n--------------------------------------------"
                +"\n* Criado por Rodrigo-RRD"
                +"\n* UNIFAN"
                +"\n* Orientador: Ricardo"
                +"\n------------METODOS DE ORDENACAO------------\n";
    }
    
    /** 
     * @return String
     */
    public String metodoUsado(){
        return "\n* Qual o metodo deseja utilizar para ordenar?"
                +"\n--------------------------------------------"
                +"\n- QuickSort(1)"
                +"\n- HeapSort(2)"
                +"\n- MergeSort(3)"
                +"\n- BubleSort(4)"
                +"\n--------------------------------------------";
    }
}
