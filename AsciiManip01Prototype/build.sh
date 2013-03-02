#!/bin/bash
gcc poc2.c -o a.out
gcc poc2.c -static -o a.out.static
objdump -d a.out > a.out.objdump
objdump -d a.out.static > a.out.static.objdump


