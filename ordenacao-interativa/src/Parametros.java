import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import orden.BubleSort;
import orden.HeapSort;
import orden.MergeSort;
import orden.QuickSort;
import orden.util.Texto;
import orden.util.Vetor;

public class Parametros {

    public void retorno() {
        Scanner sc = new Scanner(System.in);
        Texto tx = new Texto();

        System.out.println(tx.introducao());

        int ordenacao = lerMetodo(sc, tx);

        Vetor vetor = new Vetor();
        try {
            vetor.inserirVetor(sc);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida, informe apenas numeros inteiros.");
            return;
        }
        int[] valores = vetor.getValorVetor();

        System.out.println("Antes : " + Arrays.toString(valores));

        switch (ordenacao) {
            case 1 -> new QuickSort().quickSort(valores, 0, valores.length - 1);
            case 2 -> new HeapSort().heapSort(valores);
            case 3 -> new MergeSort().mergeSort(valores);
            case 4 -> new BubleSort().bubleSort(valores);
        }

        System.out.println("\n****************************************\n");
        System.out.println("Ordenado : " + Arrays.toString(valores));
        System.out.println("\n****************************************\n");
    }

    private int lerMetodo(Scanner sc, Texto tx) {
        while (true) {
            System.out.println(tx.metodoUsado());
            if (sc.hasNextInt()) {
                int opcao = sc.nextInt();
                if (opcao >= 1 && opcao <= 4) {
                    return opcao;
                }
            } else if (!sc.hasNext()) {
                throw new IllegalStateException("Entrada encerrada.");
            } else {
                sc.next();
            }
            System.out.println("Opcao invalida, escolha de 1 a 4.");
        }
    }
}
