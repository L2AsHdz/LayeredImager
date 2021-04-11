#!/bin/bash

echo Compilando Lexer...
jflex capLexer.flex
echo ----------------------------------------------------------------------

echo Compilando Parser...
cup -parser CapParser -symbols CapSym capParser.cup

mv CapLexer.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/com/l2ashdz/layeredimager/analizador/lexico/
mv CapParser.java CapSym.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/com/l2ashdz/layeredimager/analizador/sintactico/
