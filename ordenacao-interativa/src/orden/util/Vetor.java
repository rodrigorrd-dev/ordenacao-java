package orden.util;

import java.util.Scanner;

public class Vetor {

    private int[] valorVetor;
    private int tamanho;

    public void inserirVetor(Scanner sc) {
        System.out.println("Insira o tamanho do vetor: ");
        tamanho = sc.nextInt();
        while (tamanho < 0) {
            System.out.println("Tamanho invalido, insira um valor >= 0: ");
            tamanho = sc.nextInt();
        }
        valorVetor = new int[tamanho];
        for (int i = 0; i < valorVetor.length; i++) {
            System.out.println("* Insira o valor da posicao " + i + ": ");
            valorVetor[i] = sc.nextInt();
        }
    }

    
    /** 
     * @return int[]
     */
    public int[] getValorVetor() {
        return this.valorVetor;
    }

    
    /** 
     * @return int
     */
    public int getTamanho() {
        return this.tamanho;
    }

}
