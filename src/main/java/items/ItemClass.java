package items;

import java.math.BigDecimal;

/**
 * Represents an item with various attributes such as name, number, price, category, picture, and inventory count.
 * Implements Comparable interface to allow comparison based on item number.
 */
public class ItemClass implements Comparable<ItemClass>{

    // attributes
    private String itemName;
    private int itemNumber;
    private BigDecimal price; //item number can be a 4 digit number for now
    private String category; // holds name of item photo
    private String itemPicture;
    private int inventoryCount;
    

    /**
     * Default constructor initializes item attributes with default values.
     */
    public ItemClass(){
        this.itemName = "Item Name";
        this.itemNumber = 0000; 
        this.price = BigDecimal.valueOf(00.00);
        this.category = "Unknown";
        this.itemPicture = "error.png";
        this.inventoryCount = 0;

    }
    /**
     * Constructor initializes item attributes with provided values.
     *
     * @param itemName       The name of the item.
     * @param itemNumber     The number of the item.
     * @param price          The price of the item.
     * @param category       The category of the item.
     * @param itemPicture    The picture of the item.
     * @param inventoryCount The count of the item in inventory.
     */
    public ItemClass(String itemName, int itemNumber, BigDecimal price, String category, String itemPicture, int inventoryCount){
        this.itemName = itemName;
        this.itemNumber = itemNumber; 
        this.price = price;
        this.category = category;
        this.itemPicture = itemPicture;
        this.inventoryCount = inventoryCount;

    }

    // methods of class

    // Getters
    public String getItemName(){return this.itemName;}
    public int getItemNumber(){return this.itemNumber;}
    public BigDecimal getPrice(){return this.price;}
    public String getCategory(){return this.category;}
    public String getItemPicture(){return this.itemPicture;}
    public int getInventoryCount(){return this.inventoryCount;}

    // Setters
    public void setItemName(String itemName){this.itemName = itemName;}
    public void setItemNumber(int itemNumber){this.itemNumber = itemNumber; }
    public void setPrice(BigDecimal price){this.price = price;}
    public void setCategory(String category){this.category = category;}
    public void setItemPicture(String itemPicture){this.itemPicture = itemPicture;}
    public void setInventoryCount(int inventoryCount){this.inventoryCount = inventoryCount;}

    /**
     * Print the details of the item.
     */
    public void printItem(){
        System.out.print(this.itemNumber + "\t");
        System.out.print(this.itemName + "\t");
        System.out.print(this.price + "\t");
        System.out.print(this.category + "\t");
        System.out.print(this.itemPicture + "\t");
        System.out.print(this.inventoryCount+ "\n");

    }
    /**
     * Returns a string representation of the item.
     *
     * @return A string representation of the item.
     */
    @Override
    public String toString() {
        return this.itemNumber + "\t" +
        this.itemName + "\t" +
        this.price + "\t" +
        this.category + "\t" +
        this.itemPicture + "\t" +
        this.inventoryCount + "\t";
    }
    
    /**
     * Reduces the inventory count of the item by the specified amount.
     *
     * @param count The amount to reduce the inventory count by.
     */
    public void reduceInventoryCount(int count){
        this.inventoryCount = this.inventoryCount - count;
    }
    /**
     * Compares this item with another item based on their item numbers.
     *
     * @param o The item to be compared.
     * @return A negative integer, zero, or a positive integer if this item is less than, equal to, or greater than the specified item.
     */
    @Override
    public int compareTo(ItemClass o) {
        if(this.itemNumber > o.itemNumber) {return 1;}
        else if(this.itemNumber < o.itemNumber) {return -1;}
        else {return 0;}
    }


    
}