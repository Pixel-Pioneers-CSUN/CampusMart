package items;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;


/**
 * Utility class for accessing and manipulating databases.
 * Note: Works only with MySQL and requires JDBC driver.
 */
public class DatabaseUtility {

    private String url;
    private String user;
    private String password; 
    private String schema;
    private String table;
    private String query;


    // default constructor
    public DatabaseUtility() {
        this.schema = "driftmer_CampusMart";
        this.user = "driftmer_pixelpioneer";
        this.password = "COMP380Group";
        this.url = "jdbc:mysql://cp01-wa.privatesystems.net/" + schema;
        this.query = "";
        this.table = "";

    }
    /**
     * Constructor with parameters.
     *
     * @param schema   The name of the database schema.
     * @param table    The name of the database table.
     * @param user     The username for accessing the database.
     * @param password The password for accessing the database.
     * @param url      The URL of the database.
     */
    public DatabaseUtility(String schema, String table, String user, String password, String url) {
        this.schema =  schema;
        this.user = user;
        this.password = password;
        this.url = url + schema;
        this.query = "";
        this.table = "";

    }
    /**
     * Sets the query for database operations.
     *
     * @param q The query to be set.
     */
    public void setQuery (String q) {this.query = q;}
    
    /**
     * Sets the table for database operations.
     *
     * @param t The table name to be set.
     */
    public void setTable (String t) {this.table = t;}


    
    /**
     * Prints out the data from the table.
     */
    public void printTable() {
        try {

            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statment = connection.createStatement();
            //add user input or make one that 
            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statment.executeQuery(printQuery);
            // get total columns in table
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            // prints names of Columns to terminal
            for(int i = 1; i <= numberOfColumns; i++) {
           
                String columnName = rsmd.getColumnName(i);
                System.out.printf("%-20s", columnName);
                

            }
            System.out.println();

            //print all the data in table
            while (resultSet.next()) {
                for(int i = 1; i <= numberOfColumns; i++) {
                   
                    String columnValue = resultSet.getString(i);
                    System.out.printf("%-20s", columnValue);
    
                }
                System.out.println();
                
            }

            connection.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Creates a TreeSet data structure containing ItemClass objects.
     *
     * @return A TreeSet containing ItemClass objects.
     */
    public Set<ItemClass> createDataStructureItemClass() {

        Set<ItemClass> tree = new TreeSet<ItemClass>();
        try {
            
            // creating connection to the database. 
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statment = connection.createStatement();
             
            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statment.executeQuery(printQuery);
            // end of connection to database
            
            //get all info from database to create a DataStructureItemClass
            while (resultSet.next()) {
                
                String itemName = resultSet.getString("itemName");
                int itemNumber = resultSet.getInt("itemNumber");
                BigDecimal price = new BigDecimal(resultSet.getString("price"));
                String category = resultSet.getString("category");
                String itemPicture = resultSet.getString("itemPicture");
                int inventoryCount = resultSet.getInt("inventoryCount");                
                ItemClass temp = new ItemClass(itemName, itemNumber, price, category, itemPicture, inventoryCount);
                tree.add(temp);   
            }
            connection.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }

        return tree;
    }

    /**
     * Creates a HashMap data structure containing ItemClass objects.
     *
     * @return A HashMap containing ItemClass objects.
     */
    public HashMap<Integer, ItemClass> createHasMapItemClass() {
        HashMap<Integer, ItemClass> map = new HashMap<>();
        try {
            
            // creating connection to the database. 
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statment = connection.createStatement();
             
            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statment.executeQuery(printQuery);
            // end of connection to database
            
            //get all info from database to create a DataStructureItemClass
            while (resultSet.next()) {
                
                String itemName = resultSet.getString("itemName");
                int itemNumber = resultSet.getInt("itemNumber");
                BigDecimal price = new BigDecimal(resultSet.getString("price"));
                String category = resultSet.getString("category");
                String itemPicture = resultSet.getString("itemPicture");
                int inventoryCount = resultSet.getInt("inventoryCount");                
                ItemClass temp = new ItemClass(itemName, itemNumber, price, category, itemPicture, inventoryCount);
                /*change to HashMap entry*/
                map.putIfAbsent(itemNumber, temp);
                

                 
            }
            connection.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return map;



    }
    
    public void updateItemDatabaseInventory(Integer itemNumber) {
        // use the datastructure class to get the object
        ItemDataStructure data = ItemDataStructure.getInstance();
        // get temp item structure
        ItemClass temp = data.getItemDataStructure().get(itemNumber);

        String sqlStatment = "UPDATE itemtable SET inventoryCount = " + temp.getInventoryCount() + " WHERE itemNumber = " + itemNumber;

        
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statment = connection.createStatement();
            statment.executeUpdate(sqlStatment);
            connection.close();
        } 
        
        catch (SQLException e) {
            
            e.printStackTrace();
        }


    }

}
