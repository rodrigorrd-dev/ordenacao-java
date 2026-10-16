package orden;

public class HeapSort {
    
    /** 
     * @param arr[]
     */
    public void heapSort(int arr[]) {
        int n = arr.length;
        // Construir o heap (reorganizar array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // Extrair elementos do heap um por um
        for (int i = n - 1; i >= 0; i--) {
            // Trocar o elemento raiz com o último elemento
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Chama o heapify no heap reduzido
            heapify(arr, i, 0);
        }
    }

    
    /** 
     * @param arr[]
     * @param n
     * @param i
     */
    public static void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}
