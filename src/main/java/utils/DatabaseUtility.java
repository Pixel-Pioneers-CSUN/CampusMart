package utils;

import controllers.HeaderBarController;
import items.ItemClass;
import items.ItemDataStructure;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import orders.Orders;
import Account.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;



/** @version 3/20/24
 * @author Erick Espinoza & Sevan Shahijanian
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

    public String getQuery() {return this.query;}

    /**
     * Sets the table for database operations.
     *
     * @param t The table name to be set.
     */
    public void setTable (String t) {this.table = t;}

    // makes it easier to just call this for connection
    // helper funcion to make code look nicer
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // a helper function to execute a sql statement regarding the database
    public  void executeSQLStatement (String query) {
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(query);
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

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
     * Creates a HashMap data structure containing ItemClass objects.
     *
     * @return A HashMap containing ItemClass objects.
     */
    public HashMap<Integer, ItemClass> createHashMapItemClass() {
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

    /**
     * Updates the inventory count of an item in the database.
     *
     * @param itemNumber The number identifying the item whose inventory count needs to be updated.
     */
    public void updateItemDatabaseInventory(Integer itemNumber) {
        // Use the data structure class to get the object
        ItemDataStructure data = ItemDataStructure.getInstance();
        // Get the temporary item structure
        ItemClass temp = data.getItemDataStructure().get(itemNumber);

        // Construct SQL statement to update inventory count
        String sqlStatement = "UPDATE itemtable SET inventoryCount = " + temp.getInventoryCount() + " WHERE itemNumber = " + itemNumber;

        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();

            // Execute SQL statement to update inventory count
            statement.executeUpdate(sqlStatement);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    *** the following is preliminary code for when user is logged in... need to work out implementation later
    public static void changeScene(ActionEvent event, String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(DatabaseUtility.class.getResource(fxmlFilePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new user account
     *
     * @param event the action event passed in for navigation purposes
     * @param name the user's first and last name
     * @param username the user's login username
     * @param password the user's login password
     */
    public void createAccount(ActionEvent event, String name, String username, String password) {
        Connection connection = null;   // connection to the database
        PreparedStatement psInsert = null;  // used to query the database - inserts valid user data into database
        PreparedStatement psCheckIfUserExists = null;   // used to query the database - checks if user already exits
        ResultSet resultSet = null; // contains the data returned from our database when we query it

        try {
            connection = DriverManager.getConnection(this.url, this.user, this.password);
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
                psInsert = connection.prepareStatement("INSERT INTO users (name, username, password) VALUES (?, ?, ?)");
                psInsert.setString(1, name);
                psInsert.setString(2, username);
                psInsert.setString(3, password);
                psInsert.executeUpdate();

                changeScene(event, "/view/HomeScreen.fxml");
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

    public void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(this.url, this.user, this.password);
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            System.out.println("the username being passed into the db check is " + username);
            if (!resultSet.isBeforeFirst()) {
                System.out.print("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The provided username is incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        // user has successfully logged in
                        System.out.println(username + " has successfully logged in!");
                        Account account = Account.getInstance();

                        // initialize the user's info into the Account singleton
                        account.setAccountID(resultSet.getInt("accountID"));
                        System.out.println("Account ID is " + account.getAccountID());
                        account.setUsername(username);
                        System.out.println("Name is " + account.getName());
                        account.setName(resultSet.getString("name"));
                        account.setPassword(password);
                        account.setLoggedInStatus(true);

                        // redirect user to the home screen
                        changeScene(event, "/view/HomeScreen.fxml");
                    } else {
                        System.out.println("Passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided password is incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // close database connections in the end to free resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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

    /**
     * Retrieves a list of orders associated with a given customer ID from the database.
     *
     * @param customerID The ID of the customer whose orders are to be retrieved.
     * @return A list of Orders objects associated with the specified customer ID.
     */
    public List<Orders> createOrderList(int customerID) {
        List<Orders> orderList = new ArrayList<>();
        this.setTable("Orders_Database");

        try {

            // creating connection to the database.
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();

            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statement.executeQuery(printQuery);
            // end of connection to database

            //get all info from database to create a DataStructureItemClass
            while (resultSet.next()) {
                //need to create a orderItems HashMap
                if(customerID == resultSet.getInt("customerID")) {

                    int orderID = resultSet.getInt("orderID");
                    HashMap<Integer, Integer> map = this.createOrderItemsHashMap(orderID);
                    // fix this later
                    String date = resultSet.getString("orderDate");

                    BigDecimal totalPrice = resultSet.getBigDecimal("orderTotal");
                    Orders newEntry = new Orders(orderID,customerID, date ,totalPrice,map);
                    orderList.add(newEntry);
                }


            }
            connection.close();

        } catch(Exception e){
            System.out.println("Failed to create Orders List!");
            e.printStackTrace();
        }

        return orderList;
    }

    /**
     * Retrieves a HashMap of order items associated with a given order ID from the database.
     *
     * @param orderID The ID of the order whose items are to be retrieved.
     * @return A HashMap containing the item numbers as keys and the corresponding quantities as values.
     */
    public HashMap<Integer,Integer> createOrderItemsHashMap(int orderID) {
        HashMap<Integer,Integer> temp = new HashMap<>();
        this.setTable("OrderItems_Database");
        try {

            // creating connection to the database.
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();

            String printQuery = "select * from " + this.table;
            ResultSet resultSet = statement.executeQuery(printQuery);
            // end of connection to database

            //get all info from database to create a DataStructureItemClass
            while (resultSet.next()) {
                if(orderID == resultSet.getInt("orderNumber")) {
                    Integer itemNumber = resultSet.getInt("itemNumber");
                    Integer itemCount = resultSet.getInt("itemCount");
                    temp.putIfAbsent(itemNumber, itemCount);
                }

            }

            connection.close();

        } catch(Exception e){
            System.out.println("Failed to create OrderItems HashMap from DatabaseUtility!");
            e.printStackTrace();
        }
        return temp;

    }
    /**
     * Updates the Orders database with the provided SQL query.
     *
     * @param query The SQL query to update the Orders database.
     */
    public void updateOrderDatabase(String query) {
        this.executeSQLStatement(query);
    }

    /**
     * Updates the OrderItems database with the provided SQL query.
     *
     * @param query The SQL query to update the OrderItems database.
     */
    public void updateOrderItemsDatabase(String query) {
        this.executeSQLStatement(query);
    }

    public String getLoggedInUserInfo(String loggedInUserName , String targetField){
        String retrievedData = "";
        try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement statement = connection.prepareStatement("SELECT "+ targetField +" FROM " + this.table + " WHERE username = ?")) {
            statement.setString(1, loggedInUserName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    retrievedData = resultSet.getString(targetField);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exception
        }

        return retrievedData;
    }

    public int saveProfileInfoToDB(String infoToUpdate , String updatedInfo, String loggedInUserName){
        int updatedInfoCount=-1;
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(" UPDATE " + this.table + " SET "
                    + infoToUpdate + " = ? WHERE username = ?");
            statement.setString(1, updatedInfo);
            statement.setString(2, loggedInUserName);
            updatedInfoCount = statement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedInfoCount;
    }
}
