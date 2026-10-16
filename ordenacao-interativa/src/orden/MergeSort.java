package orden;

public class MergeSort {
    
    /** 
     * @param arr
     */
    public void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int meio = arr.length / 2;
        int[] metadeEsquerda = new int[meio];
        int[] metadeDireita = new int[arr.length - meio];

        for (int i = 0; i < meio; i++) {
            metadeEsquerda[i] = arr[i];
        }
        for (int i = meio; i < arr.length; i++) {
            metadeDireita[i - meio] = arr[i];
        }
        mergeSort(metadeEsquerda);
        mergeSort(metadeDireita);
        merge(arr, metadeEsquerda, metadeDireita);
    }

    
    /** 
     * @param arr
     * @param metadeEsquerda
     * @param metadeDireita
     */
    private static void merge(int[] arr, int[] metadeEsquerda, int[] metadeDireita) {
        int i = 0, j = 0, k = 0;
        while (i < metadeEsquerda.length && j < metadeDireita.length) {
            if (metadeEsquerda[i] <= metadeDireita[j]) {
                arr[k++] = metadeEsquerda[i++];
            } else {
                arr[k++] = metadeDireita[j++];
            }
        }
        while (i < metadeEsquerda.length) {
            arr[k++] = metadeEsquerda[i++];
        }
        while (j < metadeDireita.length) {
            arr[k++] = metadeDireita[j++];
        }
    }
}
