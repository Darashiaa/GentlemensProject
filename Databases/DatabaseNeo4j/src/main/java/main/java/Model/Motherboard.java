/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.Model;

/**
 *
 * @author sharelison
 */
public class Motherboard {
    
    private Double price;
    private String name;
    private String desc;
    private String photo;
    private String memorySocket;
    private String memoryType;
    private String socket;
    private String formFactor;
    private String link;
    private String site;
    private String label;
    
    public Motherboard(String price, String name, String desc, String photo, String memorySocket,
            String memoryType, String formFactor,  String socket, String link, String site, String label) {
        price = price.replaceAll(",", ".");
        formFactor = formFactor.replaceAll("µ", "");
        formFactor = formFactor.replaceAll("\\s+", "");
        this.price = Double.parseDouble(price);
        this.name = name;
        this.desc = desc; 
        this.photo = photo;
        this.memorySocket = memorySocket;
        this.memoryType = memoryType;
        this.socket = socket;
        this.formFactor = formFactor.trim();
        this.link = link;
        this.site = site;
        this.label = label;
    }

    public String getLabel() {
        return label;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMemorySocket() {
        return memorySocket;
    }

    public void setMemorySocket(String memorySocket) {
        this.memorySocket = memorySocket;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
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

    public void setSite(String website) {
        this.site = website;
    }
    
}
