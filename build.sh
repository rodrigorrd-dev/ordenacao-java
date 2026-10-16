#!/usr/bin/env bash
# Compila a biblioteca e gera build/ordenacao.jar (tambem copiado para exemplo-uso/lib/).
set -euo pipefail
cd "$(dirname "$0")"
rm -rf build && mkdir -p build/classes exemplo-uso/lib
javac --release 17 -d build/classes $(find src/main/java -name '*.java')
jar --create --file build/ordenacao.jar -C build/classes .
cp build/ordenacao.jar exemplo-uso/lib/
echo "Gerado: build/ordenacao.jar"
