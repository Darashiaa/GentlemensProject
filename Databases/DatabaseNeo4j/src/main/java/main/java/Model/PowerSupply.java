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
public class PowerSupply {
    
    private Double price;
    private String name;
    private String desc;
    private String photo;
    private String energy;
    private String formFactor;
    private String link;

    public PowerSupply(String price, String name, String desc, String photo, 
            String energy, String formFactor, String link, String site, String lable) {
        price = price.replaceAll(",", ".");
        this.price = Double.parseDouble(price);
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.energy = energy;
        this.formFactor = formFactor;
        this.link = link;
        this.site = site;
        this.lable = lable;
    }
    private String site;
    private String lable;

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

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
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

    public void setSite(String site) {
        this.site = site;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
