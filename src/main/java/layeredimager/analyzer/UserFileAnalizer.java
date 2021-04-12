package layeredimager.analyzer;

import java.io.StringReader;
import java.util.List;
import layeredimager.analizador.lexico.UserLexer;
import layeredimager.analizador.sintactico.UserParser;
import layeredimager.model.user.InfoUser;

/**
 *
 * @date 11/04/2021
 * @time 21:00:56
 * @author asael
 */
public class UserFileAnalizer implements Analyzer {
    
    private UserLexer lexer;
    private UserParser parser;

    @Override
    public void analyze(String text) {
        StringReader reader = new StringReader(text);
        try {
        lexer = new UserLexer(reader);
        parser = new UserParser(lexer);
        parser.parse();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public List<InfoUser> getInfoUsers() {
        return parser.getInfoUsers();
    }
}
