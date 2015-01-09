
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import static main.java.Controller.DatabaseController.graphdb;
import main.java.Model.Motherboard;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import java.util.HashMap;

/**
 *
 * @author sharelison
 */
public class MotherboardController extends ReadController{
      
       private String price;
       private String name;
       private String photo;
       private String desc;
       private String socket;
       private String link;
       private String site;
       private String modules;
       private String formFactor;
       private String memoryType;
       final private String seperatorData = "<<<";
       HashMap<String, String> hashMap = new HashMap<String, String>();
       
       public MotherboardController() {
           hashMap.put(priceProperty, "-");
           hashMap.put(nameProperty,"-");
           hashMap.put(photoProperty, "-");
           hashMap.put(descProperty, "-");
           hashMap.put(socketProperty, "-");
           hashMap.put(linkProperty, "-");
           hashMap.put(siteProperty,"-");
           hashMap.put(modulesProperty, "-");
           hashMap.put(formFactorProperty, "-");
           hashMap.put(memoryTypeProperty, "-");

           assignValuesToHashMap();
       }
        
        public void fillData(DatabaseController db,String csvFile, String label){
        ArrayList <String[]> fieldsArrays = readFile(csvFile);
           for (String[] fields : fieldsArrays) {
               try {
                   assignValuesToAttributes(fields);
                   assignValuesToHashMap();
                   Motherboard motherboard = new Motherboard(price, name, desc, photo,
                           modules, memoryType, formFactor, socket, link, site, label);
                   fill(motherboard);
                   updateMySQLDatabase(price, name, link, photo, site);
               }
               catch(Exception eobe) {
                   eobe.printStackTrace();
               }  }
    }
       
    public void fill(Motherboard motherboard) {
             
        Transaction transaction = graphdb.beginTx();
        try {
          Label label = DynamicLabel.label(motherboard.getLabel());
          nodeIndex = DatabaseController.graphdb.index().forNodes("exact");
          Node node = DatabaseController.graphdb.createNode(label);
          node.setProperty(nameProperty, motherboard.getName());
          node.setProperty(priceProperty, motherboard.getPrice());
          node.setProperty(linkProperty, motherboard.getLink());
          node.setProperty(photoProperty, motherboard.getPhoto());
          node.setProperty(descProperty, motherboard.getDesc());
          node.setProperty(socketProperty, motherboard.getSocket());
          node.setProperty(memoryTypeProperty, motherboard.getMemoryType());
          node.setProperty(modulesProperty, motherboard.getMemorySocket());
          node.setProperty(siteProperty, motherboard.getSite());
          node.setProperty(formFactorProperty, motherboard.getFormFactor());   
          
          addRelations(node, motherboard);
          nodeIndex.add(node,nameProperty, motherboard.getName());
          transaction.success();
          
        } finally {
          transaction.finish();
        }
    }
    
        private void assignValuesToAttributes(String[] fields) {
            for(int t = 0; t < fields.length; t++) {     
             String[] specifications = new String[10];
             specifications[0] = "-";
             specifications[1] = "-";
             specifications = fields[t].split(seperatorData);
             try {
                hashMap.put(specifications[0], specifications[1]);
             }
             catch(ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                hashMap.put(specifications[0], "-");
             }
        }
    }
    
    private void assignValuesToHashMap() {                  
           price = hashMap.get(priceProperty);
           name = hashMap.get(nameProperty);
           photo = hashMap.get(photoProperty);
           desc = hashMap.get(descProperty);
           socket = hashMap.get(socketProperty);
           link = hashMap.get(linkProperty);
           site = hashMap.get(siteProperty);
           modules = hashMap.get(modulesProperty);
           formFactor = hashMap.get(formFactorProperty);
           memoryType = hashMap.get(memoryTypeProperty);
    }
    
    public void addRelations(Node node, Motherboard motherboard) {                    
            Label labelProcessor = DynamicLabel.label("Processor");
            Iterator<Node> nodesProcessor = graphdb.findNodesByLabelAndProperty(labelProcessor, socketProperty, motherboard.getSocket().trim()).iterator();
            while(nodesProcessor.hasNext()) {
                Node processorNode = nodesProcessor.next();
                node.createRelationshipTo(processorNode, Relationship.IS_COMPATIBLE_WITH);
                processorNode.createRelationshipTo(node, Relationship.IS_COMPATIBLE_WITH);

            }
            
            Label labelPowerSupply = DynamicLabel.label("Memory");   
            String memorySocket = getMemoryType(motherboard.getMemoryType());
            System.out.println(memorySocket);
            Iterator<Node> nodesMemory = graphdb.findNodesByLabelAndProperty(labelPowerSupply, memoryTypeProperty, memorySocket).iterator();
            while(nodesMemory.hasNext()) {
                Node memoryNode = nodesMemory.next();
                if(memoryNode.getProperty(modulesProperty).equals(motherboard.getMemorySocket().trim())) {
                    node.createRelationshipTo(memoryNode, Relationship.IS_COMPATIBLE_WITH);
                    memoryNode.createRelationshipTo(node, Relationship.IS_COMPATIBLE_WITH);
                }
            }

    }
}
