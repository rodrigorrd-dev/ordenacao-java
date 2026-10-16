#!/usr/bin/env bash
# Compila e roda o benchmark. Uso: ./benchmark.sh [repeticoes]  (padrao: 3)
set -euo pipefail
cd "$(dirname "$0")"
[ -f lib/ordenacao.jar ] || ../build.sh
mkdir -p out
javac -cp lib/ordenacao.jar -d out src/Benchmark.java
java -cp out:lib/ordenacao.jar Benchmark "$@"
