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
public class Processor {
private Double price;
private String name;
private String label;
private String photo;
private String link;
private String site;
private String desc;
private String socket;

public Processor(String price,String name, String desc,
        String photo, String socket, String link,String site, String label) {
        price = price.replaceAll(",", ".");
        this.price = Double.parseDouble(price);
        this.name = name;
        this.photo = photo;
        this.link = link;
        this.site = site;
        this.desc = desc; 
        this.socket = socket;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}
