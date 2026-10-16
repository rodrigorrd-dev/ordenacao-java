package orden;

public class BubleSort {

	
	/** 
	 * @param vet
	 */
	public void bubleSort(int[] vet) {
		int aux = 0;
		for (int i = 0; i < vet.length; i++) {
			for (int j = 0; j < vet.length - 1 - i; j++) {
				if (vet[j] > vet[j + 1]) {
					aux = vet[j];
					vet[j] = vet[j + 1];
					vet[j + 1] = aux;
				}
			}
		}
	}
}
