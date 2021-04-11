package layeredimager.analizador.lexico;

import java_cup.runtime.Symbol;
import static layeredimager.analizador.sintactico.ImageSym.*;

%%

%class ImageLexer
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
%%

<YYINITIAL> {
    "{"                         {return symbol(OPEN_BRACE);}
    "}"                         {return symbol(CLOSE_BRACE);}
    ","                         {return symbol(COMMA);}
    \s                          {/**Ignorar*/}
}

<YYINITIAL> {ENTERO}            {return symbol(ENTERO);}

[^]                             {System.out.println("Error " + yytext());}