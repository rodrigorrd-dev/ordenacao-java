package dev.rodrigorrd.ordenacao;

import java.util.Arrays;

/** Merge sort: O(n log n) em todos os casos, estavel, usa O(n) de memoria extra. */
public class MergeSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {
        if (vetor.length <= 1) {
            return;
        }
        int meio = vetor.length / 2;
        int[] esquerda = Arrays.copyOfRange(vetor, 0, meio);
        int[] direita = Arrays.copyOfRange(vetor, meio, vetor.length);
        ordenar(esquerda);
        ordenar(direita);
        mesclar(vetor, esquerda, direita);
    }

    private static void mesclar(int[] vetor, int[] esquerda, int[] direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.length && j < direita.length) {
            vetor[k++] = esquerda[i] <= direita[j] ? esquerda[i++] : direita[j++];
        }
        while (i < esquerda.length) {
            vetor[k++] = esquerda[i++];
        }
        while (j < direita.length) {
            vetor[k++] = direita[j++];
        }
    }
}
