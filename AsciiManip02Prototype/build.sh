#!/bin/bash
gcc -ggdb poc3.c -o a.out
gcc -ggdb poc3.c -static -o a.out.static
objdump -d a.out > a.out.objdump
objdump -d a.out.static > a.out.static.objdump


