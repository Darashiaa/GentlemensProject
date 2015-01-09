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
public class PcPart {
    private Double price; 
    private String name;
    private String photo;
    private String link;
    private String site;

    public PcPart(String price, String name, String link, String photo, String site) {
        price = price.replaceAll(",", ".");
        this.price = Double.parseDouble(price);
        this.name = name;
        this.photo = photo;
        this.link = link;
        this.site = site;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
}
