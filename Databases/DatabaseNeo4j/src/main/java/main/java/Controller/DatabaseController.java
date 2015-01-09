package main.java.Controller;

import java.io.File;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sharelison
 */
public class DatabaseController extends Controller{
    public final static String path = "C:\\PcPartsDb";
    protected static GraphDatabaseService graphdb;

    private void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphdb.shutdown();
            }
        });
    }   
    
    public void startDatabase() {
        deleteFileOrDirectory(new File(path));
        graphdb = new GraphDatabaseFactory().newEmbeddedDatabase(path);
        Transaction transaction = graphdb.beginTx();
        try {
            nodeIndex = graphdb.index().forNodes("nodes");
            transaction.success();
        } finally {
            transaction.finish();
        }
        this.registerShutdownHook(graphdb);
    }
    
    public void stopDatabase() {
        graphdb.shutdown();
    }
    
    private static void deleteFileOrDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File child : file.listFiles()) {
                    deleteFileOrDirectory(child);
                }
            }
            file.delete();
        }
    }
}
