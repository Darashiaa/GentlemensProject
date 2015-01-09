/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Controller;

/**
 *
 * @author sharelison
 */

import java.sql.*;
import java.util.Date;
import main.java.Model.PcPart;
public class MySQLDatabaseController {
   
    private Connection connection;
    final private String Url = "jdbc:mysql://localhost:3306/test?user=eddy&password=0000";
    public MySQLDatabaseController() {

    }
    
    public void addProductToDatabase(PcPart product) throws SQLException{
            if(!productInDatabase(product.getLink())) {
                String queryProduct = "INSERT INTO product (productname, price, link, photo, clicked) VALUES (?,?,?,?,?)";
                PreparedStatement statement =  connection.prepareStatement(queryProduct);
                statement.setString(1, product.getName());
                statement.setDouble(2,product.getPrice());
                statement.setString(3, product.getLink());
                statement.setString(4, product.getPhoto());
                statement.setInt(5, 0);
                statement.executeUpdate();
                addPriceAndTime(product);
            }
            else {
                addPriceAndTime(product);
            }
    }
    
    public void addPriceAndTime(PcPart product) throws SQLException{       
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                String queryPrice = "INSERT INTO price (price, linkproduct, currenttime) VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(queryPrice);
                statement.setDouble(1, product.getPrice());
                statement.setString(2, product.getLink());
                statement.setTimestamp(3, timestamp);
                statement.executeUpdate();

    }
    
    public boolean productInDatabase(String link) throws SQLException {
            String query = "Select * from product where link = '" + link + "'"; 
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
    }
    
    public void closeConnectionDb() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            
        }
    }
    
    public void connectToDb() {
        try {
            connection = DriverManager.getConnection(Url);
        }
        catch(SQLException e) {
            
        }
    } 
}
