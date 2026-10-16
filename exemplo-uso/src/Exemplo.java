import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import dev.rodrigorrd.ordenacao.Ordenador;
import dev.rodrigorrd.ordenacao.BubbleSort;
import dev.rodrigorrd.ordenacao.HeapSort;
import dev.rodrigorrd.ordenacao.MergeSort;
import dev.rodrigorrd.ordenacao.QuickSort;

public class Exemplo {

    public static void main(String[] args) {
        Map<String, Ordenador> algoritmos = new LinkedHashMap<>();
        algoritmos.put("BubbleSort", new BubbleSort());
        algoritmos.put("HeapSort", new HeapSort());
        algoritmos.put("MergeSort", new MergeSort());
        algoritmos.put("QuickSort", new QuickSort());

        int[] pequeno = {42, 7, -3, 19, 7, 0, 100, -58, 23, 5};
        System.out.println("== Vetor pequeno ==");
        System.out.println("Original: " + Arrays.toString(pequeno));
        algoritmos.forEach((nome, algoritmo) ->
                System.out.printf("%-10s -> %s%n", nome, Arrays.toString(algoritmo.ordenado(pequeno))));

        int[] grande = new Random(42).ints(20_000, -100_000, 100_000).toArray();
        int[] esperado = grande.clone();
        Arrays.sort(esperado);

        System.out.println("\n== 20.000 numeros aleatorios ==");
        algoritmos.forEach((nome, algoritmo) -> {
            int[] copia = grande.clone();
            long inicio = System.nanoTime();
            algoritmo.ordenar(copia);
            long ms = (System.nanoTime() - inicio) / 1_000_000;
            System.out.printf("%-10s %5d ms  %s%n", nome, ms,
                    Arrays.equals(copia, esperado) ? "OK" : "FALHOU");
        });
    }
}
