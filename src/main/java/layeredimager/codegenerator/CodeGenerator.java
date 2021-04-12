package layeredimager.codegenerator;

/**
 *
 * @date 28/03/2021
 * @time 19:38:14
 * @author asael
 */
public abstract class CodeGenerator {
    
    protected StringBuilder text;
    
    public abstract String generate();
    
    protected void addLine(String s, int tabulaciones) {

        for (int i = 0; i < tabulaciones; i++) {
            text.append("    ");
        }

        text.append(s).append("\n");
    }
}
