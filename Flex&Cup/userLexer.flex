package com.l2ashdz.layeredimager.analizador.lexico;

import java_cup.runtime.Symbol;
import static com.l2ashdz.layeredimager.analizador.sintactico.UserSym.*;

%%

%class UserLexer
%public
%cup
%unicode
%line
%column

%{

    private Symbol symbol(int type){
        return new Symbol(type, yytext());
    }

%}

%eofval{
    return new Symbol(EOF, "Fin de linea");
%eofval}

ENTERO = (0|([1-9][0-9]*))
NAME = \w(\w|\d)*
%%

<YYINITIAL> {
    ","                         {return symbol(COMMA);}
    ";"                         {return symbol(SEMI);}
    ":"                         {return symbol(COLON);}
    \s                          {/**Ignorar*/}
}

<YYINITIAL> {ENTERO}            {return symbol(ENTERO);}
<YYINITIAL> {NAME}              {return symbol(NAME);}

[^]                             {System.out.println("Error " + yytext());}