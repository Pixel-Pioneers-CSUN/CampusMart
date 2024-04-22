package utils;

import controllers.HeaderBarController;
import controllers.HomeScreenController;
import items.ItemClass;
import items.ItemDataStructure;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
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
            Statement statement = connection.createStatement();
            //add user input or make one that 
            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statement.executeQuery(printQuery);
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
            System.out.println("Failed to print out items from DatabaseUtility!");
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
            Statement statement = connection.createStatement();
             
            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statement.executeQuery(printQuery);
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

        String sqlStatement = "UPDATE itemtable SET inventoryCount = " + temp.getInventoryCount() + " WHERE itemNumber = " + itemNumber;

        
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
            connection.close();
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    *** the following is preliminary code for when user is logged in... need to work out implementation later
//    public static void changeScene(ActionEvent event, String fxmlFile, String username) {
//        Parent root = null;
//        // if the user is logged in, allow them to navigate to account dashboard
//        if (username != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(DatabaseUtility.class.getResource(fxmlFile));
//                root = loader.load();
//                HeaderBarController headerBarController = loader.getController();
//                headerBarController.setUserInformation(username);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root, 1300, 800));
//        stage.show();
//    }

    public static void createAccount(String name, String username, String password) {
        Connection connection = null;   // connection to the database
        PreparedStatement psInsert = null;  // used to query the database - inserts valid user data into database
        PreparedStatement psCheckIfUserExists = null;   // used to query the database - checks if user already exits
        ResultSet resultSet = null; // contains the data returned from our database when we query it

        try {
            connection = DriverManager.getConnection("jdbc:mysql://cp01-wa.privatesystems.net/users", "driftmer_pixelpioneer", "COMP380Group");
            psCheckIfUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");    // check database if username already exists
            psCheckIfUserExists.setString(1, username);
            resultSet = psCheckIfUserExists.executeQuery();

            // if the resultSet is empty, then username is available and can proceed to create account
            if (resultSet.isBeforeFirst()) {    // isBeforeFirst() returns false if resultSet is empty
                System.out.println("Username is already taken.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username is not available.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (name, username, password VALUES (?, ?, ?))");
                psInsert.setString(1, name);
                psInsert.setString(2, username);
                psInsert.setString(3, password);
                psInsert.executeUpdate();

                //   changeScene(event, "HomeScreen.fxml", username);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally { // close database connections in the end to free resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckIfUserExists != null) {
                try {
                    psCheckIfUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void logInUser(ActionEvent event, String username, String password) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://cp01-wa.privatesystems.net/users", "driftmer_pixelpioneer", "COMP380Group");
//            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
//            preparedStatement.setString(1, username);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.isBeforeFirst()) {
//                System.out.print("User not found in the database!");
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("The provided username is incorrect!");
//                alert.show();
//            } else {
//                while (resultSet.next()) {
//                    String retrievedPassword = resultSet.getString("password");
//                    if (retrievedPassword.equals(password)) {
//                        changeScene(event, "HomeScreen.fxml", username);
//                    } else {
//                        System.out.println("Passwords did not match!");
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setContentText("The provided password is incorrect.");
//                        alert.show();
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally { // close database connections in the end to free resources
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
