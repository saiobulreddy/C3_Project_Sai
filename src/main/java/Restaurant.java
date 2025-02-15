import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    final String name;
    final String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    final List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return openingTime.isBefore(getCurrentTime()) && closingTime.isAfter(getCurrentTime());
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) if (item.getName().equals(itemName)) return item;
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null) throw new itemNotFoundException(itemName);
        menu.remove(itemToBeRemoved);
    }

    public int getOrderValue(String... orderItems) throws itemNotFoundException {
        int orderCost = 0;
        for (String item : orderItems) {
            Item itemDetails = findItemByName(item);
            if (itemDetails == null) throw new itemNotFoundException(item);
            orderCost += itemDetails.getPrice();
        }
        return orderCost;
    }
}
