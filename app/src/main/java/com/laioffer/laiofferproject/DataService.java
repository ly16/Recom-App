package com.laioffer.laiofferproject;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    /**
     * Fake all the restaurant data for now. We will refine this and connect
     * to our backend later.
     */
    public static List<Restaurant> getRestaurantData() {
        List<Restaurant> eventData = new ArrayList<Restaurant>();
        for (int i = 0; i < 10; ++i) {
            eventData.add(
                    new Restaurant("Restaurant", "1184 W valley Blvd, CA 90101",
                            "Asian"));
        }
        return eventData;
    }
}
