package dev.rodrigorrd.ordenacao;

/**
 * Quick sort (particao de Lomuto, pivo no ultimo elemento): O(n log n) em media,
 * O(n^2) no pior caso (ex.: vetor ja ordenado), in-place, nao estavel.
 * A recursao so desce no lado menor, entao a pilha cresce no maximo O(log n).
 */
public class QuickSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {
        ordenar(vetor, 0, vetor.length - 1);
    }

    private static void ordenar(int[] vetor, int baixo, int alto) {
        while (baixo < alto) {
            int p = particionar(vetor, baixo, alto);
            if (p - baixo < alto - p) {
                ordenar(vetor, baixo, p - 1);
                baixo = p + 1;
            } else {
                ordenar(vetor, p + 1, alto);
                alto = p - 1;
            }
        }
    }

    private static int particionar(int[] vetor, int baixo, int alto) {
        int pivo = vetor[alto];
        int i = baixo - 1;
        for (int j = baixo; j < alto; j++) {
            if (vetor[j] <= pivo) {
                i++;
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }
        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[alto];
        vetor[alto] = temp;
        return i + 1;
    }
}
