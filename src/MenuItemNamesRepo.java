
public class MenuItemNamesRepo implements Aggregate {  // Concrete Aggregate, Concrete Collection class

    private String[] menuItemNames;

    public MenuItemNamesRepo(String[] menuItemNames) {
        this.menuItemNames = menuItemNames;
    }

    public String[] getMenuItemNames() {
        return menuItemNames;
    }

    public void setMenuItemNames(String[] menuItemNames) {
        this.menuItemNames = menuItemNames;
    }

    @Override
    public Iterator getIterator() {
        return new MenuItemNameIterator();
    }

}
