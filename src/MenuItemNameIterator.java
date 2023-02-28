
public class MenuItemNameIterator implements Iterator {  // Concrete Iterator

    private MenuItemNamesRepo aggregate;
    int index = 0;

    public void setAggregate(MenuItemNamesRepo concreteAggregate) {
        aggregate = concreteAggregate;
    }

    @Override
    public boolean hasNext() {
        if (index < aggregate.getMenuItemNames().length) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return aggregate.getMenuItemNames()[index++];
        }
        return null;
    }

}
