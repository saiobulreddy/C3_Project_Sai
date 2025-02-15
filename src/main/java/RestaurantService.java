import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    final static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        for (Restaurant restaurant : getRestaurants())
            if (restaurant.name.equals(restaurantName)) return restaurant;
        throw new restaurantNotFoundException(restaurantName);
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public void removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null) throw new restaurantNotFoundException(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
