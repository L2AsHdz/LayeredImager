#!/bin/bash

echo Compilando Lexer...
jflex imageLexer.flex
echo ----------------------------------------------------------------------

echo Compilando Parser...
cup -parser ImageParser -symbols ImageSym imageParser.cup

mv ImageLexer.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/layeredimager/analizador/lexico/
mv ImageParser.java ImageSym.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/layeredimager/analizador/sintactico/
