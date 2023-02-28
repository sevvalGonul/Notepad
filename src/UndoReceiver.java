
public class UndoReceiver {
    GUI gui;

    public UndoReceiver(GUI gui) {
        this.gui = gui;
    }

    public void deleteLastChar() {  // Business logic
        String temp = gui.textArea.getText();
        int L = temp.length();
        if (L > 1) {
            gui.textArea.setText(temp.substring(0, L - 1));
        }
    }
}
