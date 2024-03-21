import java.math.BigDecimal;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;




/*****************************************************
 * This class has a database object that can be used to
 * accesss and manipulate databases for our project
 * 
 * ONLY WORKS IF YOU ARE USING MYSQL AND HAVE JDBC DRIVER
 * INSTALLED    
 * 
 * 
 *****************************************************/
public class DatabaseUtility {

    private String url;
    private String user;
    private String password; 
    private String schema;
    private String table;
    private String query;


    // default constructor
    public DatabaseUtility() {
        this.schema = "campusmart";
        this.user = "root";
        this.password = "PixelPioneers380!";
        this.url = "jdbc:mysql://localhost:3306/" + schema;
        this.query = "";
        this.table = "";

    }
    // constructor with parameters
    // localhost using mysql
    // ask for database table to accesss
    public DatabaseUtility(String schema, String table, String user, String password, String url) {
        this.schema =  schema;
        this.user = user;
        this.password = password;
        this.url = url + schema;
        this.query = "";
        this.table = "";

    }
    public void setQuery (String q) {this.query = q;}
    public void setTable (String t) {this.table = t;}


    // prints out the data from table
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
            System.out.println("");

            //print all the data in table
            while (resultSet.next()) {
                for(int i = 1; i <= numberOfColumns; i++) {
                   
                    String columnValue = resultSet.getString(i);
                    System.out.printf("%-20s", columnValue);
    
                }
                System.out.println("");
                
            }

            connection.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    // creates a TreeSet dataStructure 
    public Set<ItemClass> createDataStructureItemClass() {

        Set<ItemClass> tree = new TreeSet<ItemClass>();
        try {

            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statment = connection.createStatement();
             
            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statment.executeQuery(printQuery);
            
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
    

}
