/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.Controller;

import org.neo4j.graphdb.RelationshipType;

/**
 *
 * @author sharelison
 */
enum Relationship implements RelationshipType {
    IS_COMPATIBLE_WITH,
}
