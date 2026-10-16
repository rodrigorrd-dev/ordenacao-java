#!/usr/bin/env bash
# Compila e executa o exemplo usando lib/ordenacao.jar (gere-o com ../build.sh).
set -euo pipefail
cd "$(dirname "$0")"
[ -f lib/ordenacao.jar ] || ../build.sh
mkdir -p out
javac -cp lib/ordenacao.jar -d out src/Exemplo.java
java -cp out:lib/ordenacao.jar Exemplo
