package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import items.*;

public class SearchHelper {

    public static Map<Integer, ItemClass> searchItems(HashMap<Integer, ItemClass> itemHashMap, String searchTerm) {
        // perform the search based on item name or category
        return itemHashMap.entrySet().stream()
                .filter(entry -> entry.getValue().getItemName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        entry.getValue().getCategory().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
