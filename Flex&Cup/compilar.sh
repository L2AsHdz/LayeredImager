#!/bin/bash

echo Compilando Lexer...
jflex lexer.flex
echo ----------------------------------------------------------------------

echo Compilando Parser...
cup -parser Parser -symbols Sym parser.cup

mv Lexer.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/com/l2ashdz/layeredimager/analizador/lexico/
mv Parser.java Sym.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/com/l2ashdz/layeredimager/analizador/sintactico/
