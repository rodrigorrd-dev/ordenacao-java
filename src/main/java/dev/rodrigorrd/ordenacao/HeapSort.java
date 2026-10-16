package dev.rodrigorrd.ordenacao;

/** Heap sort: O(n log n) em todos os casos, in-place, nao estavel. */
public class HeapSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {
        int n = vetor.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            peneirar(vetor, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;
            peneirar(vetor, i, 0);
        }
    }

    private static void peneirar(int[] vetor, int n, int i) {
        while (true) {
            int maior = i;
            int esquerda = 2 * i + 1;
            int direita = 2 * i + 2;
            if (esquerda < n && vetor[esquerda] > vetor[maior]) {
                maior = esquerda;
            }
            if (direita < n && vetor[direita] > vetor[maior]) {
                maior = direita;
            }
            if (maior == i) {
                return;
            }
            int troca = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = troca;
            i = maior;
        }
    }
}
