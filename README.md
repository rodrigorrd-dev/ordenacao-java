# ordenacao

Algoritmos de ordenacao (Bubble, Heap, Merge e Quick) para `int[]`, empacotados como biblioteca.

```
ordenacao-java/
├── src/main/java/dev/rodrigorrd/ordenacao/   biblioteca
├── pom.xml                                   coordenadas Maven (dev.rodrigorrd:ordenacao:1.0.0)
├── build.sh                                  gera build/ordenacao.jar
├── exemplo-uso/                              projeto simples que usa a biblioteca
└── ordenacao-interativa/                     programa de console original
```

## Uso

Todas as classes implementam `Ordenador`:

```java
Ordenador o = new QuickSort();   // BubbleSort, HeapSort, MergeSort, QuickSort
o.ordenar(vetor);                // ordena o proprio vetor
int[] copia = o.ordenado(vetor); // devolve copia ordenada, original intacto
```

## Usar em outro projeto

- **Jar:** rode `./build.sh` e adicione `build/ordenacao.jar` ao classpath (ou a `lib/` do projeto).
- **Maven:** instale com `mvn install` e adicione a dependencia
  `dev.rodrigorrd:ordenacao:1.0.0`.

## Exemplo

`./exemplo-uso/executar.sh` ordena um vetor pequeno e 20.000 numeros aleatorios com cada algoritmo,
conferindo o resultado contra `Arrays.sort` e mostrando o tempo.

| Algoritmo  | Media        | Pior caso    | Estavel |
|------------|--------------|--------------|---------|
| BubbleSort | O(n²)        | O(n²)        | sim     |
| HeapSort   | O(n log n)   | O(n log n)   | nao     |
| MergeSort  | O(n log n)   | O(n log n)   | sim     |
| QuickSort  | O(n log n)   | O(n²)        | nao     |
