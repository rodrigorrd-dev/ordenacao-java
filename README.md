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

## Quando usar cada um

| Algoritmo  | Use quando... | Exemplo concreto |
|------------|---------------|------------------|
| BubbleSort | O vetor e minusculo ou quase ordenado, e simplicidade importa mais que velocidade. | Ensino; reordenar uma lista de ~10 itens em que so 1 ou 2 saíram do lugar. |
| HeapSort   | Precisa de garantia de tempo no pior caso **e** de pouca memoria extra (in-place). | Sistema embarcado/tempo real que nao pode ter surpresa de lentidao nem alocar memoria; pegar os "k maiores" de um fluxo. |
| MergeSort  | Precisa de **estabilidade** (itens iguais mantem a ordem) ou tem dados grandes demais para a memoria. | Ordenar vendas por valor depois de ja ordenadas por data: vendas de mesmo valor continuam em ordem de data. Ordenacao externa de arquivos grandes. |
| QuickSort  | Caso geral com dados em memoria e sem exigencia de estabilidade; costuma ser o mais rapido na pratica. | Ordenar milhoes de numeros/ids aleatorios (e a base da maioria das ordenacoes de primitivos em bibliotecas). |

## Por que essas expressoes (O(...))

`n` e a quantidade de elementos; `O(...)` diz como o numero de operacoes cresce quando `n` cresce.

- **O(n²) — BubbleSort:** compara pares adjacentes e varre o vetor ate `n` vezes. Sao ate `n` passadas com ate `n` comparacoes cada: ≈ n·n. Dobrar `n` quadruplica o tempo (1.000 itens ≈ 1 milhao de operacoes).
- **O(n log n) — HeapSort e MergeSort:** `log n` e quantas vezes da para dividir `n` ao meio ate chegar em 1 (1.000 itens → ~10 divisoes). O MergeSort divide o vetor ao meio ate `log n` niveis e, em cada nivel, intercala todos os `n` elementos: n · log n. O HeapSort constroi um heap e retira o maior `n` vezes, cada retirada custando `log n` (altura da arvore). 1.000 itens ≈ 10 mil operacoes, contra 1 milhao do BubbleSort.
- **QuickSort, media O(n log n):** o pivo costuma dividir o vetor em partes de tamanho parecido, dando ~`log n` niveis de `n` comparacoes.
- **QuickSort, pior caso O(n²):** se o pivo for sempre o menor ou o maior (ex.: vetor ja ordenado com pivo no extremo), cada nivel remove so 1 elemento: `n` niveis de ~`n` comparacoes.
- **Media vs. pior caso:** a media e o custo esperado em entradas tipicas; o pior caso e o limite garantido para a entrada mais azarada. Por isso HeapSort e MergeSort sao "previsiveis" e o QuickSort nao.
- **Estavel:** ordenacao estavel nao troca a ordem relativa de elementos iguais. BubbleSort e MergeSort so movem/intercalam preservando essa ordem; HeapSort e QuickSort trocam elementos distantes, o que pode desfazer essa ordem.

## Benchmark (teste com milhoes de dados)

`./exemplo-uso/benchmark.sh [repeticoes]` ordena vetores de 10 mil, 100 mil, 1 milhao e 10 milhoes de
numeros em 5 situacoes, com os 4 algoritmos. Para cada um mostra o tempo em **milissegundos** (mediana das
execucoes, resultado conferido contra `Arrays.sort`) e qual foi o mais rapido. `pulado` significa que o
algoritmo foi estimado em mais de 30 s para aquele tamanho e nao foi executado.

### Como ler as tabelas

- Cada **linha** e um tamanho `n`; cada **coluna** e um algoritmo; o numero e o tempo gasto (menor = melhor).
- Cada **bloco** e uma situacao: como os numeros chegam (aleatorios, ja ordenados, invertidos, quase ordenados
  ou com muitos valores repetidos).
- Compare **ao longo das linhas** para ver como o tempo cresce com `n`: no BubbleSort 10x mais dados custa ~100x
  mais tempo (O(n²)); nos demais custa ~10x a ~12x (O(n log n)).

### Resultados (ms, um computador de exemplo; os valores variam por maquina)

**Aleatorio** (o caso mais comum)

|          n | BubbleSort | HeapSort | MergeSort | QuickSort | Mais rapido |
|-----------:|-----------:|---------:|----------:|----------:|-------------|
|     10.000 |       74,0 |      0,6 |       0,6 |       0,5 | QuickSort   |
|    100.000 |   10.670,1 |      7,9 |       9,0 |       5,3 | QuickSort   |
|  1.000.000 |     pulado |    101,9 |     107,4 |      62,3 | QuickSort   |
| 10.000.000 |     pulado |  1.664,7 |   1.116,8 |     716,3 | QuickSort   |

**Ja ordenado**

|          n | BubbleSort | HeapSort | MergeSort | QuickSort | Mais rapido |
|-----------:|-----------:|---------:|----------:|----------:|-------------|
|     10.000 |        0,0 |      0,5 |       0,2 |      22,5 | BubbleSort  |
|    100.000 |        0,0 |      5,3 |       2,3 |   4.285,0 | BubbleSort  |
|  1.000.000 |        0,4 |     78,9 |      37,1 |    pulado | BubbleSort  |
| 10.000.000 |        4,5 |    684,2 |     304,5 |    pulado | BubbleSort  |

**Invertido**

|          n | BubbleSort | HeapSort | MergeSort | QuickSort | Mais rapido |
|-----------:|-----------:|---------:|----------:|----------:|-------------|
|     10.000 |       55,3 |      0,4 |       0,2 |      22,2 | MergeSort   |
|    100.000 |    5.910,0 |      5,4 |       2,3 |   2.276,5 | MergeSort   |
|  1.000.000 |     pulado |     61,5 |      26,4 |    pulado | MergeSort   |
| 10.000.000 |     pulado |    697,7 |     309,0 |    pulado | MergeSort   |

**Quase ordenado** (~1% fora do lugar)

|          n | BubbleSort | HeapSort | MergeSort | QuickSort | Mais rapido |
|-----------:|-----------:|---------:|----------:|----------:|-------------|
|     10.000 |       12,7 |      0,4 |       0,2 |       0,6 | MergeSort   |
|    100.000 |    1.398,4 |      5,3 |       3,3 |       8,2 | MergeSort   |
|  1.000.000 |     pulado |     61,1 |      32,6 |     123,1 | MergeSort   |
| 10.000.000 |     pulado |    695,9 |     359,2 |   1.645,6 | MergeSort   |

**Muitos repetidos** (so 10 valores diferentes)

|          n | BubbleSort | HeapSort | MergeSort | QuickSort | Mais rapido |
|-----------:|-----------:|---------:|----------:|----------:|-------------|
|     10.000 |       62,8 |      0,5 |       0,4 |       2,3 | MergeSort   |
|    100.000 |   10.712,6 |      5,5 |       4,5 |     214,0 | MergeSort   |
|  1.000.000 |     pulado |     58,5 |      58,1 |  21.031,9 | MergeSort   |
| 10.000.000 |     pulado |    649,0 |     543,7 |   pulado  | MergeSort   |

### Conclusoes

**BubbleSort**
- Foi o pior em quase tudo: 10,7 s para 100 mil numeros aleatorios, contra menos de 10 ms dos outros; acima disso foi pulado.
- So "vence" em vetor ja ordenado, porque termina na 1a passada (O(n)): 4,5 ms para 10 milhoes.
- Use apenas para vetores pequenos, ensino, ou dados que ja estao quase ordenados. E estavel e nao usa memoria extra.

**HeapSort**
- Tempo previsivel: 5 a 8 ms para 100 mil e 0,65 a 1,7 s para 10 milhoes, em qualquer situacao (O(n log n) garantido).
- Foi o mais lento dos tres O(n log n) em dados aleatorios (1,7 s contra 0,7 s do QuickSort) por acessar a memoria de forma menos eficiente.
- Ordena no proprio vetor, sem memoria extra, mas nao e estavel. Escolha quando a memoria e limitada e o tempo precisa ser garantido.

**MergeSort**
- O mais confiavel: foi o mais rapido ou empatado em 4 das 5 situacoes, e levou 0,3 a 1,1 s para 10 milhoes em qualquer uma delas.
- Perdeu so para o QuickSort em dados aleatorios (1,1 s contra 0,7 s com 10 milhoes).
- E estavel (importante ao ordenar registros por varios criterios), mas usa memoria extra O(n).
- Boa escolha padrao quando nao se sabe como os dados chegam.

**QuickSort**
- O mais rapido em dados aleatorios, em todos os tamanhos (0,7 s para 10 milhoes, ~1,5x a 2x mais rapido que Heap/Merge).
- Nesta implementacao (pivo no ultimo elemento) cai no pior caso O(n²) em vetor ja ordenado, invertido ou com muitos repetidos:
  4,3 s com 100 mil ordenados, 21 s com 1 milhao de repetidos, inviavel acima disso.
- Em dados quase ordenados ficou mais lento que Heap/Merge (1,6 s contra 0,4 a 0,7 s com 10 milhoes).
- Nao e estavel. So e seguro se os dados forem realmente aleatorios ou se o pivo for melhorado (mediana de tres ou aleatorio).

**Resumo:** dados desconhecidos ou que precisam de estabilidade → MergeSort; dados aleatorios e velocidade maxima → QuickSort;
memoria limitada com tempo garantido → HeapSort; vetor minusculo ou ja ordenado → BubbleSort.

### Qual o mais confiavel e rapido?

**MergeSort**, com base nos testes acima.

| Criterio                      | BubbleSort | HeapSort | MergeSort | QuickSort |
|-------------------------------|------------|----------|-----------|-----------|
| Rapido em dados aleatorios    | pessimo    | bom      | bom (2o)  | **o mais rapido** |
| Rapido nos outros 4 casos     | pessimo    | bom      | **o mais rapido ou empatado** | pior caso em 3 deles |
| Confiavel (sem surpresa)      | nao        | sim      | **sim, O(n log n) sempre** | nao, tem pior caso O(n²) |
| Estavel                       | sim        | nao      | **sim**   | nao       |
| Memoria extra                 | nenhuma    | nenhuma  | O(n)      | pouca     |

- **Dados desconhecidos:** MergeSort. Foi o mais rapido em 4 das 5 situacoes e seu tempo quase nao muda com a ordem
  dos dados (0,3 a 1,1 s para 10 milhoes em qualquer uma).
- **Dados aleatorios e velocidade maxima:** QuickSort (0,7 s contra 1,1 s do MergeSort com 10 milhoes), mas so depois
  de trocar o pivo por mediana de tres ou aleatorio, para eliminar o pior caso.
- **Memoria limitada:** HeapSort. Tempo garantido como o MergeSort e sem memoria extra, porem um pouco mais lento e
  nao estavel.
- **BubbleSort:** so para vetores pequenos ou ja ordenados.

Os tempos vem de um unico computador e de vetores de `int`; a ordem entre os algoritmos pode mudar em outra maquina.

### O que significam os valores altos

Os numeros das tabelas sao milissegundos (1.000 ms = 1 s). Um valor alto significa que o algoritmo demorou muito.

| Valor | Tempo real | O que aconteceu |
|-------|-----------:|-----------------|
| BubbleSort, 100.000 aleatorios: 10.670 | ~10,7 s | Compara quase todos os pares de elementos (~10 bilhoes de comparacoes). Os demais levaram menos de 10 ms. |
| QuickSort, 100.000 ja ordenados: 4.285 | ~4,3 s | Caiu no pior caso, O(n²). |
| QuickSort, 1.000.000 com muitos repetidos: 21.031 | ~21 s | Mesmo problema: muitos valores iguais geram particoes desiguais. |
| HeapSort, 10.000.000 aleatorios: 1.664 | ~1,7 s | Normal: 10 milhoes de elementos dao trabalho, mas continua O(n log n). |

Ha dois tipos de valor alto:

1. **Alto porque `n` e grande, mas proporcional** (ex.: 10 milhoes em HeapSort/MergeSort, 0,3 a 1,7 s): 10x mais dados
   custam ~10x a 12x mais tempo. E o esperado para O(n log n).
2. **Alto por causa do algoritmo (O(n²))**: 10x mais dados custam ~100x mais tempo. Acontece sempre no BubbleSort e
   no QuickSort quando o pivo e ruim.

**Por que o QuickSort demorou tanto em vetor ordenado:** esta implementacao usa o ultimo elemento como pivo. Num vetor
ordenado ele e sempre o maior, entao a particao separa 1 elemento do resto em vez de dividir ao meio. Isso gera `n`
rodadas de `n` comparacoes (`n²`): com 100 mil elementos sao ~5 bilhoes de operacoes.

**Como comparar:** olhe a proporcao entre os algoritmos na mesma linha, nao o numero isolado. Em 100.000 ja ordenados,
o MergeSort levou 2,3 ms e o QuickSort 4.285 ms (~1.800x mais). Essa diferenca e o pior caso, e e por isso que a
escolha do algoritmo importa.
