/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Controller;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author UR mama
 */
public class RelationshipController extends Controller {
    public void linkMotherboardToCase(Node Motherboard, Node Case) {
        try (Transaction tx = DatabaseController.graphdb.beginTx()) {

            relationship = Motherboard.createRelationshipTo(Case, Relationship.IS_COMPATIBLE_WITH);

            tx.success();
        }
    }
    
}
