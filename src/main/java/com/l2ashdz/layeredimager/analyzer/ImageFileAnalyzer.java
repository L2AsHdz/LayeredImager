package com.l2ashdz.layeredimager.analyzer;

import com.l2ashdz.layeredimager.analizador.lexico.ImageLexer;
import com.l2ashdz.layeredimager.analizador.sintactico.ImageParser;
import com.l2ashdz.layeredimager.model.image.PreImagen;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @date 11/04/2021
 * @time 14:45:32
 * @author asael
 */
public class ImageFileAnalyzer implements Analyzer {

    private ImageLexer lexer;
    private ImageParser parser;
    
    @Override
    public void analyze(String text) {
        StringReader reader = new StringReader(text);
        
        try {
            lexer = new ImageLexer(reader);
            parser = new ImageParser(lexer);
            parser.parse();
        } catch (Exception e) {
        }
    }
    
    public List<PreImagen> getPreImagenes() {
        return parser.getPreImagenes();
    }

}
