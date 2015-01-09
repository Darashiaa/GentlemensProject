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
public class Memory {
    private Double price;
    private String name;
    private String desc;
    private String photo;
    private String moduleType;
    private String modules;
    private String link;
    private String site;
    private String label;
    
    public Memory(String price,String name, String desc,
        String photo, String moduleType, String modules, String link, String site, String label) {
        price = price.replaceAll(",", ".");
        if (moduleType.contains("-")) {
            String[] moduleTypes = moduleType.split("-");
            this.moduleType = moduleTypes[1] + "" + moduleTypes[0];
        } else {
            this.moduleType = moduleType;
        }
        this.price = Double.parseDouble(price);
        this.name = name;
        this.photo = photo;
        this.link = link;
        this.site = site;
        this.desc = desc; 
        this.modules = modules;
        this.moduleType = moduleType;
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

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
