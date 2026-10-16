import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.IntFunction;

import dev.rodrigorrd.ordenacao.BubbleSort;
import dev.rodrigorrd.ordenacao.HeapSort;
import dev.rodrigorrd.ordenacao.MergeSort;
import dev.rodrigorrd.ordenacao.Ordenador;
import dev.rodrigorrd.ordenacao.QuickSort;

/**
 * Mede o tempo de cada algoritmo em varios tamanhos e situacoes de entrada
 * e indica o mais rapido de cada situacao. Uso: java Benchmark [repeticoes]
 */
public class Benchmark {

    static final int[] TAMANHOS = {10_000, 100_000, 1_000_000, 10_000_000};
    /** Um algoritmo e pulado se, extrapolando como O(n^2) a partir do tamanho anterior, passar disso. */
    static final double LIMITE_ESTIMADO_MS = 30_000;
    /** Se uma unica execucao passar disso, nao repete (so mede uma vez). */
    static final double LIMITE_REPETIR_MS = 5_000;

    public static void main(String[] args) {
        int repeticoes = args.length > 0 ? Integer.parseInt(args[0]) : 3;

        Map<String, Ordenador> algoritmos = new LinkedHashMap<>();
        algoritmos.put("BubbleSort", new BubbleSort());
        algoritmos.put("HeapSort", new HeapSort());
        algoritmos.put("MergeSort", new MergeSort());
        algoritmos.put("QuickSort", new QuickSort());

        Map<String, IntFunction<int[]>> cenarios = new LinkedHashMap<>();
        cenarios.put("aleatorio", n -> new Random(1).ints(n, -1_000_000, 1_000_000).toArray());
        cenarios.put("ja ordenado", n -> { int[] v = new int[n]; for (int i = 0; i < n; i++) v[i] = i; return v; });
        cenarios.put("invertido", n -> { int[] v = new int[n]; for (int i = 0; i < n; i++) v[i] = n - i; return v; });
        cenarios.put("quase ordenado", n -> quaseOrdenado(n));
        cenarios.put("muitos repetidos", n -> new Random(1).ints(n, 0, 10).toArray());

        aquecer(algoritmos);
        Map<String, double[]> ultimo = new java.util.HashMap<>();

        System.out.printf("Mediana de ate %d execucoes, tempos em ms ('pulado' = estimado em mais de 30 s)%n", repeticoes);
        for (var cenario : cenarios.entrySet()) {
            System.out.printf("%n=== %s ===%n", cenario.getKey());
            System.out.printf("%8s", "n");
            algoritmos.keySet().forEach(nome -> System.out.printf("%13s", nome));
            System.out.printf("   mais rapido%n");

            for (int n : TAMANHOS) {
                int[] base = cenario.getValue().apply(n);
                int[] esperado = base.clone();
                Arrays.sort(esperado);

                System.out.printf("%,10d", n);
                String melhor = "-";
                double menor = Double.MAX_VALUE;
                for (var alg : algoritmos.entrySet()) {
                    String chave = cenario.getKey() + "/" + alg.getKey();
                    double[] anterior = ultimo.get(chave); // {n, ms}
                    if (anterior != null) {
                        double razao = (double) n / anterior[0];
                        if (anterior[1] * razao * razao > LIMITE_ESTIMADO_MS) {
                            System.out.printf("%13s", "pulado");
                            continue;
                        }
                    }
                    double ms = medir(alg.getValue(), base, esperado, repeticoes);
                    ultimo.put(chave, new double[] {n, ms});
                    System.out.printf("%,13.1f", ms);
                    if (ms < menor) {
                        menor = ms;
                        melhor = alg.getKey();
                    }
                }
                System.out.printf("   %s%n", melhor);
            }
        }
    }

    /** Mediana do tempo (ms) de ordenar copias de {@code base}; aborta se o resultado estiver errado. */
    static double medir(Ordenador alg, int[] base, int[] esperado, int repeticoes) {
        double[] tempos = new double[repeticoes];
        for (int r = 0; r < repeticoes; r++) {
            int[] copia = base.clone();
            long inicio = System.nanoTime();
            alg.ordenar(copia);
            tempos[r] = (System.nanoTime() - inicio) / 1e6;
            if (!Arrays.equals(copia, esperado)) {
                throw new IllegalStateException(alg.getClass().getSimpleName() + " ordenou errado");
            }
            if (tempos[r] > LIMITE_REPETIR_MS) {
                tempos = Arrays.copyOf(tempos, r + 1);
                break;
            }
        }
        Arrays.sort(tempos);
        return tempos[tempos.length / 2];
    }

    /** Deixa a JVM compilar os algoritmos antes de medir, para nao penalizar o primeiro. */
    static void aquecer(Map<String, Ordenador> algoritmos) {
        int[] base = new Random(7).ints(5_000, 0, 100_000).toArray();
        for (int i = 0; i < 20; i++) {
            for (Ordenador alg : algoritmos.values()) {
                alg.ordenar(base.clone());
            }
        }
    }

    /** Vetor ordenado em que ~1% das posicoes foram trocadas de lugar. */
    static int[] quaseOrdenado(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) v[i] = i;
        Random rnd = new Random(1);
        for (int k = 0; k < Math.max(1, n / 100); k++) {
            int a = rnd.nextInt(n), b = rnd.nextInt(n);
            int t = v[a]; v[a] = v[b]; v[b] = t;
        }
        return v;
    }
}
