
public class Main {

    public static void main(String[] args) {
        GUI notepad = GUI.getInstance();
        UndoReceiver receiver = new UndoReceiver(notepad);
        notepad.setUndoCommand(new UndoCommand(receiver));
        notepad.createUI();  // template method in NotepadTemplate is called

    }
}
