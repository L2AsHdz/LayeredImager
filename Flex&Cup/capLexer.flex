package layeredimager.analizador.lexico;

import java_cup.runtime.Symbol;
import static layeredimager.analizador.sintactico.CapSym.*;

%%

%class CapLexer
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
COLOR = #([a-fA-F0-9]{6}|[a-fA-F0-9]{3})
%%

<YYINITIAL> {
    "{"                         {return symbol(OPEN_BRACE);}
    "}"                         {return symbol(CLOSE_BRACE);}
    ","                         {return symbol(COMMA);}
    ";"                         {return symbol(SEMI);}
    \s                          {/**Ignorar*/}
}

<YYINITIAL> {ENTERO}            {return symbol(ENTERO);}
<YYINITIAL> {COLOR}             {return symbol(COLOR);}

[^]                             {System.out.println("Error " + yytext());}