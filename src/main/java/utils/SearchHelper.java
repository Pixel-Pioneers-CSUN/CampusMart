package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import items.*;

/**
 * @version 3/20/24
 * @author Sevan Shahijanian
 * Utility class for searching items from the item hash map based on various search criteria.
 * The Item Hash Map, which itself pulls data from the database, plays a key role and serves as the source of searchable items.
 */
public class SearchHelper {

    /**
     * Searches for an item in the provided item hash map based on the given item name or category.
     *
     * @param itemHashMap The map containing items.
     * @param searchTerm  The term to be searched for within an item's name or category.
     * @return A map containing the items to match the search term.
     */
    public static Map<Integer, ItemClass> searchItems(HashMap<Integer, ItemClass> itemHashMap, String searchTerm) {
        // perform the search based on item name or category
        return itemHashMap.entrySet().stream()
                .filter(entry -> entry.getValue().getItemName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        entry.getValue().getCategory().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Returns an item from the provided item hash map based on its name.
     *
     * @param name    The name of the item to return.
     * @param itemMap The hash map containing items.
     * @return If found, the item with the specified name. Otherwise, return null.
     */
    public static ItemClass getItemByName(String name, Map<Integer, ItemClass> itemMap) {
        for (ItemClass item : itemMap.values()) {
            if (item.getItemName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}

