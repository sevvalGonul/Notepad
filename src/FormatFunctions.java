
import java.awt.Font;


public class FormatFunctions {
    GUI gui;
    //Font arial, tnr;
    String selectedFont;
    int selectedFontSize;
    
    public FormatFunctions(GUI gui) {
        this.gui = gui;
        
    }
    
    public void createFont(int fontSize) {
        //arial = new Font("Arial", Font.PLAIN, fontSize);
        //tnr = new Font("Times New Roman", Font.PLAIN, fontSize);
        
        selectedFontSize = fontSize;
        setSelectedFont(selectedFont);
    }
    
    public void setSelectedFont(String fontName) {
        /*selectedFont = font;
        
        switch(selectedFont) {
            case "Arial":
                gui.textArea.setFont(arial);
                break;
            case "Times New Roman":
                gui.textArea.setFont(tnr);
                break;
        } */
        selectedFont = fontName;
        IFont font = FontFactory.createFont(fontName, selectedFontSize, gui.textArea.getFont().isBold(), gui.textArea.getFont().isItalic());
       
        gui.textArea.setFont(font.getFont());
    }
}
