/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Model;

/**
 *
 * @author UR mama
 */
public class Videocard {
    private Double price; 
    private String name; 
    private String desc; 
    private String photo; 
    private String memoryType; 

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }
    private String link; 
    private String site;
    private String label;
    
    public Videocard(String price, String name, String desc, String photo, 
            String memoryType, String link, String site, String label) {
        price = price.replaceAll(",", ".");
        this.price = Double.parseDouble(price);
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.memoryType = memoryType;
        this.link = link;
        this.site = site;
        this.label = label;
    
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDesc(){
        return desc;
    }
    
    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
