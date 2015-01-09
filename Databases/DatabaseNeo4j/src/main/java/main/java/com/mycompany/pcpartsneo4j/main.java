/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sharelison
 */

package main.java.com.mycompany.pcpartsneo4j;

import main.java.Controller.CaseController;
import main.java.Controller.DatabaseController;
import main.java.Controller.HarddiskController;
import main.java.Controller.MemoryController;
import main.java.Controller.MotherboardController;
import main.java.Controller.PowerSupplyController;
import main.java.Controller.ProcessorController;
import main.java.Controller.VideocardController;

public class main {
    final private static DatabaseController db = new DatabaseController();
    final private static ProcessorController pcp = new ProcessorController();
    final private static MotherboardController mc = new MotherboardController();
    final private static MemoryController gh = new MemoryController();
    final private static CaseController cc = new CaseController();
    final private static VideocardController vc = new VideocardController();
    final private static HarddiskController hd = new HarddiskController();
    final private static PowerSupplyController psc = new PowerSupplyController();
       
    public static void main(String[] args) {
         db.startDatabase();
           System.out.println("Loading processors into database....");
           pcp.fillData(db,"C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\processorAzerty.txt", "Processor");
           pcp.fillData(db,"C:\\Users\\sharelison\\PycharmProjects\\untitled\\processorsAlternate.txt", "Processor");
           System.out.println("Loaded all processors into database.");
           
           System.out.println("Loading memory's into database....");
           gh.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\geheugenAzertytest.txt", "Memory");
           gh.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\untitled\\geheugenAlternate.txt", "Memory");
           System.out.println("Loaded all memory's into database.");
           
           System.out.println("Loading videocards into database....");
           vc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\grafischKaartAzerty.txt", "Videocard");
           vc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\untitled\\grafischKaartAlternate.txt", "Videocard");
           System.out.println("Loaded all videocards into database.");
           
           System.out.println("Loading Harddisks into database....");
           hd.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\harddiskAzerty.txt", "Harddisk");
           hd.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\untitled\\harddiskAlternate.txt", "Harddisk");
           System.out.println("Loaded all Harddisks into database.");
           
           System.out.println("Loading Power Supply's into database....");
           psc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\untitled\\voedingAlternate.txt", "Power Supply");
           psc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\voedingAzerty.txt", "Power Supply");
           System.out.println("Loaded all Power Supply's into database.");
           
           System.out.println("Loading Motherboards into database....");
           mc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\untitled\\moederboardAlternate.txt", "Motherboard");
           mc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\moederboardAzerty.txt", "Motherboard");
           System.out.println("Loaded all Motherboards into database.");
           
           System.out.println("Loading Cases into database....");
           cc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\crawlAzerty\\behuizingAzerty.txt", "Case");
           cc.fillData(db, "C:\\Users\\sharelison\\PycharmProjects\\untitled\\behuizingAlternate.txt", "Case");
           System.out.println("Loaded all Cases into database.");
    }
}
