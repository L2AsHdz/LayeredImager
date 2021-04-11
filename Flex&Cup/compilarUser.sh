#!/bin/bash

echo Compilando Lexer...
jflex userLexer.flex
echo ----------------------------------------------------------------------

echo Compilando Parser...
cup -parser UserParser -symbols UserSym userParser.cup

mv UserLexer.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/layeredimager/analizador/lexico/
mv UserParser.java UserSym.java /home/asael/NetBeansProjects/LayeredImager/src/main/java/layeredimager/analizador/sintactico/
