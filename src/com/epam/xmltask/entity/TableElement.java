package com.epam.xmltask.entity;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:59 AM
 */
public class TableElement {
    private String category;
    private String subCategory;
    private String unit;
    private String provider;
    private String model;
    private Date dateOfIssue;
    private String color;
    private Integer price;
    private Boolean stock;

    public TableElement() {
    }
    public TableElement(TableElement tableElement) {
       
       this.setCategory(tableElement.getCategory());
       this.setSubCategory(tableElement.getSubCategory());
       this.setUnit(tableElement.getUnit());
       this.setProvider(tableElement.getProvider());
       this.setModel(tableElement.getModel());
       this.setDateOfIssue(tableElement.getDateOfIssue());
       this.setPrice(tableElement.getPrice());
       this.setStock(tableElement.getStock());
       
    }

    @Override
    public String toString() {
        return category+subCategory+unit;
    }

    public TableElement(String category,
                        String subCategory,
                        String unit,
                        String provider,
                        String model,
                        Date dateOfIssue,
                        String color) {
        this.category = category;
        this.subCategory = subCategory;
        this.unit = unit;
        this.provider = provider;
        this.model = model;
        this.dateOfIssue = dateOfIssue;
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

       public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
