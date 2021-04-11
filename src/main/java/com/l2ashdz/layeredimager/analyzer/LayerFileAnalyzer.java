package com.l2ashdz.layeredimager.analyzer;

import com.l2ashdz.layeredimager.analizador.lexico.CapLexer;
import com.l2ashdz.layeredimager.analizador.sintactico.CapParser;
import com.l2ashdz.layeredimager.edd.tree.ArbolAVL;
import java.io.StringReader;

/**
 *
 * @date 11/04/2021
 * @time 13:55:58
 * @author asael
 */
public class LayerFileAnalyzer implements Analyzer {
    
    private CapLexer lexer;
    private CapParser parser;

    @Override
    public void analyze(String text) {
        StringReader reader = new StringReader(text);
        
        try {
            lexer = new CapLexer(reader);
            parser = new CapParser(lexer);
            parser.parse();
        } catch (Exception e) {
        }
    }
    
    public ArbolAVL getCapas() {
        return parser.getCapas();
    }

}
