
public class UndoCommand implements ICommand {

    private UndoReceiver undoReceiver;
    
    public UndoCommand(UndoReceiver undoReceiver) {  // Constructor Injection
        this.undoReceiver = undoReceiver;
    }

    @Override
    public void execute() {
        this.undoReceiver.deleteLastChar();
    }

}
