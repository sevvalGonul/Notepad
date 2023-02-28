
import java.awt.Font;
import javax.swing.JTextArea;

public class BoldTextFormatStrategy implements TextFormatStrategy {

    public void formatText(JTextArea textArea) {
        Font currentFont = textArea.getFont();
        textArea.setFont(currentFont.deriveFont(Font.BOLD));
    }
}
