
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI extends NotepadTemplate implements ActionListener {
    //JFrame window;
    JTextArea textArea;  // Multiline area that displays plain text
    JScrollPane scroll;  // For scrollable text area
    JMenuBar menuBar;  // Top Menu Bar
    JMenu fileMenu, editMenu, formatMenu;  // Items in Top Menu Bar
    // EDIT MENU:
    //JMenuItem undo, redo, myUndo;
    // FILE MENU:
    //JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit;  // Sub menu items
    // FORMAT MENU:
    JMenu menuFont, menuFontSize;
    //JMenuItem fontArial, fontTNR, fontSize10, fontSize12, fontSize14, fontSize16, fontSize20, fontSize22;

    FileFunctions file = new FileFunctions(this);
    FormatFunctions format = new FormatFunctions(this);
    EditFunctions edit = new EditFunctions(this);

    UndoManager undoManager = new UndoManager();
    String[] fileMenuItemNames = {"New", "Open", "Save", "Save As", "Exit"};
    String[] editMenuItemNames = {"Undo", "Redo", "My Undo"};
    String[] fontMenuItemNames = {"Arial", "Times New Roman"};
    String[] fontSizeMenuItemNames = {"10", "12", "14", "16", "20", "22", "24"};
    
    private static volatile GUI guiInstance;

    private final int defaultSize = 20;
    private ICommand undoCommand;
    private TextFormatContext textFormatContext;

    private GUI() {
        textFormatContext = new TextFormatContext();
    }

    public void setUndoCommand(ICommand undoCommand) {  // GUI is Invoker class, responsible for initiating request
        this.undoCommand = undoCommand;
    }

    public static GUI getInstance() {
        if (guiInstance == null) {
            synchronized (GUI.class) {
                if (guiInstance == null) {
                    guiInstance = new GUI();
                }
            }
        }
        return guiInstance;
    }


    public void createTextArea() {
        textArea = new JTextArea();

        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        window.add(scroll);
        //window.add(textArea);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        formatMenu = new JMenu("Format");
        menuBar.add(formatMenu);

    }

    public void createFileMenu() {
        
        addMenuItems(fileMenuItemNames, fileMenu, this);

    }

    public void createEditMenu() {
        // Command design pattern kullanarak kendi yaptığımız undo edit menuye My Undo ismiyle eklenmiştir
        addMenuItems(editMenuItemNames, editMenu, this);
    }

    public void createFormatMenu() {
        menuFont = new JMenu("Font");
        formatMenu.add(menuFont);

        menuFontSize = new JMenu("Font Size");
        formatMenu.add(menuFontSize);
        
        addMenuItems(fontMenuItemNames, menuFont, this);
        
        addMenuItems(fontSizeMenuItemNames, menuFontSize, this);

        // Strategy Design Pattern concrete class'larının format menüye bağlanması için kod:
        formatMenu.add(createFormatMenuItem("Bold", "bold"));
        formatMenu.add(createFormatMenuItem("Italic", "italic"));
        formatMenu.add(createFormatMenuItem("Bold And Italic", "bolditalic"));

    }

    public void setDefaultValues() {
        // Default values for font and font size:
        format.selectedFont = "Arial";
        format.createFont(defaultSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            int size = Integer.parseInt(command);
            format.createFont(size);
        } catch (NumberFormatException ex) {
            switch (command) {
                case "New":
                    file.newFile();
                    break;
                case "Open":
                    file.open();
                    break;
                case "Save":
                    file.save();
                    break;
                case "Save As":
                    file.saveAs();
                    break;
                case "Exit":
                    file.exit();
                    break;
                case "Arial":
                    format.setSelectedFont(command);
                    break;
                case "Times New Roman":
                    format.setSelectedFont(command);
                    break;
                case "Undo":
                    edit.undo();
                    break;
                case "Redo":
                    edit.redo();
                    break;
                case "My Undo":
                    undoCommand.execute();
            }
        }
    }


    private void addMenuItems(String[] itemNames, JMenu jmenu, ActionListener listener) {
        MenuItemNamesRepo repo = new MenuItemNamesRepo(itemNames);
        Iterator iterator = repo.getIterator();  // It returns a MenuItemNameIterator
        ((MenuItemNameIterator) iterator).setAggregate(repo);
        while (iterator.hasNext()) {
            String itemName = (String) iterator.next();
            JMenuItem item = new JMenuItem(itemName);
            item.addActionListener(this);
            item.setActionCommand(itemName);
            jmenu.add(item);

        }
    }

    private JMenuItem createFormatMenuItem(String label, final String format) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTextFormat(format);
            }
        });
        return menuItem;
    }

    public void setTextFormat(String format) {
        if (format.equalsIgnoreCase("bold")) {
            textFormatContext.setTextFormatStrategy(new BoldTextFormatStrategy());

        } else if (format.equalsIgnoreCase("italic")) {
            textFormatContext.setTextFormatStrategy(new ItalicTextFormatStrategy());
        } else if (format.equalsIgnoreCase("bolditalic")) {
            textFormatContext.setTextFormatStrategy(new BoldAndItalicTextFormatStrategy());
        }
        textFormatContext.formatText(textArea);
    }

}
