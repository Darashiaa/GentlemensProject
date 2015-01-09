/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.Controller;

import java.sql.SQLException;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import main.java.Model.PcPart;
/**
 *
 * @author sharelison
 */
public class Controller {
   DatabaseController dbController;
   MySQLDatabaseController dbmysql = new MySQLDatabaseController();
    Index<Node> nodeIndex;
    final String nameProperty = "Name";
    final String priceProperty = "Price";
    final String descProperty = "Description";
    final String photoProperty = "Photo";
    final String modulesProperty = "Modules";
    final String memoryTypeProperty = "MemoryType";
    final String formFactorProperty = "Formfactor";
    final String energyProperty = "Energy";
    final String linkProperty = "Link";
    final String siteProperty = "Website";
    final String socketProperty = "Socket";
    Relationship relationship;
    
    public void updateMySQLDatabase(String price, String name, String link, String photo, String site) throws SQLException{
        dbmysql.connectToDb();
        dbmysql.addProductToDatabase(new PcPart(price, name, link, photo, site));
        dbmysql.closeConnectionDb();
    }
    
    public String getMemoryType(String memoType) {
        memoType = memoType.trim();
        String memorySocket = memoType;
        String[] memoTypes;
        if(memoType.contains(" ")) {
            memoTypes = memoType.split("\\s+");
        }
        else if(memoType.contains("-")) {
            memoTypes = memoType.split("-");
        }
        else {
            return memoType;
        }
   
        if(memoTypes.length > 0) {
            if(!memoTypes[0].equals("SDRAM")) {
                memorySocket = memoTypes[0];
            }
            else {
                memorySocket = memoTypes[1];
            }
        }        
        return memorySocket.trim();
    }
}
