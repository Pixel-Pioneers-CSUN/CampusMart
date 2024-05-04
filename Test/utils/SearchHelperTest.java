package utils;

import items.ItemClass;
import items.ItemDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import utils.SearchHelper;
import utils.DatabaseUtility;

import static org.junit.jupiter.api.Assertions.*;

class SearchHelperTest {

    @BeforeEach
    void setUp() {
        DatabaseUtility db = new DatabaseUtility();
        db.setTable("Item_Database");
        ItemDataStructure data = ItemDataStructure.getInstance();
        data.setItemDataStructure(db.createHashMapItemClass());
    }

    @Test
    @DisplayName("Typing in a search term should return all items directly related to the search term.")
    void searchItemsTest1() {
        String apple = "Blueberry";
        Map<Integer, ItemClass> searchResults = SearchHelper.searchItems(ItemDataStructure.getInstance().getItemDataStructure(), apple);
        assertEquals("Blueberry Muffin", searchResults.get(62).getItemName());
    }

    @Test
    @DisplayName("Typing in an incomplete search term should return all items related to the search term.")
    void searchItemsTest2() {
        String apple = "Blueber";
        Map<Integer, ItemClass> searchResults = SearchHelper.searchItems(ItemDataStructure.getInstance().getItemDataStructure(), apple);
        assertEquals("Blueberries", searchResults.get(37).getItemName());
        assertEquals("Blueberry Muffin", searchResults.get(62).getItemName());
    }

    @Test
    @DisplayName("Typing an invalid search term should yield an empty hash map")
    void searchItemsTest3() {
        String aNumber = "1";
        Map<Integer, ItemClass> searchResults = SearchHelper.searchItems(ItemDataStructure.getInstance().getItemDataStructure(), aNumber);
        assertEquals(true, searchResults.isEmpty());
    }

    @Test
    @DisplayName("Retrieving a search item from the items hash map should return the correct item.")
    void getItemByNameTest1() {
        String selectedName = "Banana";
        ItemClass selectedItem = SearchHelper.getItemByName(selectedName, ItemDataStructure.getInstance().getItemDataStructure());
        assertEquals(30, selectedItem.getItemNumber());
    }

    @Test
    @DisplayName("A search term for an item that doesn't exist in the items hash map should return null.")
    void getItemByNameTest2() {
        String selectedName = "Bannana";
        ItemClass selectedItem = SearchHelper.getItemByName(selectedName, ItemDataStructure.getInstance().getItemDataStructure());
        assertEquals(null, selectedItem);
    }
}