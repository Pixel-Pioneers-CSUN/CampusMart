package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import items.*;

public class SearchHelper {

    /**
     * Searches for items in the provided item map based on the given search term, matching either the item name or category.
     *
     * @param itemHashMap The map containing items with their corresponding IDs.
     * @param searchTerm  The term to search for within item names or categories.
     * @return A map containing the IDs and corresponding items that match the search term.
     */
    public static Map<Integer, ItemClass> searchItems(HashMap<Integer, ItemClass> itemHashMap, String searchTerm) {
        // perform the search based on item name or category
        return itemHashMap.entrySet().stream()
                .filter(entry -> entry.getValue().getItemName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        entry.getValue().getCategory().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Retrieves an item from the provided item map based on its name.
     *
     * @param name    The name of the item to retrieve.
     * @param itemMap The map containing items with their corresponding IDs.
     * @return The item with the specified name (if found). Otherwise, returns null.
     */
    public static ItemClass getItemByName(String name, Map<Integer, ItemClass> itemMap) {
        // Iterate through each item in the itemMap
        for (ItemClass item : itemMap.values()) {
            // check if the name matches an actual item name. If it does, return the item itself
            if (item.getItemName().equals(name)) {
                return item;
            }
        }
        // if no matching item name found, return null
        return null;
    }
}
