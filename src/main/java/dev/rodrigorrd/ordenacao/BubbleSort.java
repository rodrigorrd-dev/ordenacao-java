package dev.rodrigorrd.ordenacao;

/** Bubble sort: O(n^2), estavel, in-place. */
public class BubbleSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            boolean trocou = false;
            for (int j = 0; j < vetor.length - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                    trocou = true;
                }
            }
            if (!trocou) {
                return;
            }
        }
    }
}
