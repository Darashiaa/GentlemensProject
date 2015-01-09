/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import static main.java.Controller.DatabaseController.graphdb;
import main.java.Model.PowerSupply;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author sharelison
 */
public class PowerSupplyController extends ReadController{
    
       private String price;
       private String name;
       private String photo;
       private String desc;
       private String link;
       private String site;
       private String formFactor;
       private String energy;
       final private String seperatorData = "<<<";
       HashMap<String, String> hashMap = new HashMap<String, String>();
       
       public PowerSupplyController() {
           hashMap.put(priceProperty, "-");
           hashMap.put(nameProperty,"-");
           hashMap.put(photoProperty, "-");
           hashMap.put(descProperty, "-");
           hashMap.put(linkProperty, "-");
           hashMap.put(siteProperty,"-");
           hashMap.put(formFactorProperty, "-");
           hashMap.put(energyProperty, "-");

           assignValuesToHashMap();
       }
    public void fillData(DatabaseController db,String csvFile, String label){
        ArrayList <String[]> fieldsArrays = readFile(csvFile);
        for (String[] fields : fieldsArrays) {
            try {
                assignValuesToAttributes(fields);
                assignValuesToHashMap();
                PowerSupply ps = new PowerSupply(price, name, desc, photo,
                        energy, formFactor, link, site, label);
                fill(ps);
                updateMySQLDatabase(price, name, link, photo, site);
            }
            catch(Exception eobe) {
                 eobe.printStackTrace();
            }
        }
    }
       
    public void fill(PowerSupply powerS) {
             
        Transaction transaction = graphdb.beginTx();
        try {
          Label label = DynamicLabel.label(powerS.getLable());
          nodeIndex = DatabaseController.graphdb.index().forNodes("exact");
          Node node = DatabaseController.graphdb.createNode(label);
          node.setProperty(nameProperty, powerS.getName());
          node.setProperty(priceProperty, powerS.getPrice());
          node.setProperty(linkProperty, powerS.getLink());
          node.setProperty(photoProperty, powerS.getPhoto());
          node.setProperty(descProperty, powerS.getDesc());
          node.setProperty(energyProperty, powerS.getEnergy());
          node.setProperty(formFactorProperty, powerS.getFormFactor());
          node.setProperty(siteProperty, powerS.getSite());
          nodeIndex.add(node,nameProperty, powerS.getName());
          transaction.success();
        } finally {
          transaction.finish();
        }
    }
    
    private void assignValuesToAttributes(String[] fields) {
      for(int t = 0; t < fields.length; t++) {                    
           String[] specifications = fields[t].split(seperatorData);
           hashMap.put(specifications[0], specifications[1]);
        }
    }
    
    private void assignValuesToHashMap() {                  
           price = hashMap.get(priceProperty);
           name = hashMap.get(nameProperty);
           photo = hashMap.get(photoProperty);
           desc = hashMap.get(descProperty);
           link = hashMap.get(linkProperty);
           site = hashMap.get(siteProperty);
           formFactor = hashMap.get(formFactorProperty);
           energy = hashMap.get(energyProperty);
    }
}
