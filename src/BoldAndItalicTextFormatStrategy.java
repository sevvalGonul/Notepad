
import java.awt.Font;
import javax.swing.JTextArea;

public class BoldAndItalicTextFormatStrategy implements TextFormatStrategy {

    @Override
    public void formatText(JTextArea textArea) {
        Font currentFont = textArea.getFont();
        textArea.setFont(currentFont.deriveFont(Font.BOLD | Font.ITALIC));
    }

}
