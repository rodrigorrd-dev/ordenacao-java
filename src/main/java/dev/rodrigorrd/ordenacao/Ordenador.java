package dev.rodrigorrd.ordenacao;

/**
 * Contrato comum a todos os algoritmos de ordenacao da biblioteca.
 */
public interface Ordenador {

    /**
     * Ordena o vetor em ordem crescente, modificando o proprio vetor.
     *
     * @param vetor vetor a ordenar; nao pode ser nulo
     */
    void ordenar(int[] vetor);

    /**
     * Retorna uma copia ordenada do vetor, sem alterar o original.
     *
     * @param vetor vetor de origem; nao pode ser nulo
     * @return nova copia, em ordem crescente
     */
    default int[] ordenado(int[] vetor) {
        int[] copia = vetor.clone();
        ordenar(copia);
        return copia;
    }
}
