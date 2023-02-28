
import javax.swing.JFrame;

public abstract class NotepadTemplate {

    JFrame window;

    public final void createUI() {  // Template method
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        setDefaultValues();
        show();
    }

    public void createWindow() {
        window = new JFrame("Notepad");  // Inıtıalizing JFrame, Notepad text is going to appear on the topbar
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        window.setVisible(true);
    }

    public abstract void createTextArea();
    public abstract void createMenuBar();
    public abstract void createFileMenu();
    public abstract void createEditMenu();
    public abstract void createFormatMenu();
    public abstract void setDefaultValues();   
}
