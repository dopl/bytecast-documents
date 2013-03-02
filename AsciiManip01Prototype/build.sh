#!/bin/bash
gcc -ggdb poc2.c -o a.out
gcc -ggdb poc2.c -static -o a.out.static
objdump -S -d a.out > a.out.objdump
objdump -S -d a.out.static > a.out.static.objdump


