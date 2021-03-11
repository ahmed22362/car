package com.example.myapplication;

public class Car {
    private int id;
    private String model;
    private String color;
    private String description;
    private String img;
    private double DPL;

    public Car(int id, String model, String color, String description, String img, double DPL) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.description = description;
        this.img = img;
        this.DPL = DPL;
    }

    public Car(String model, String color, String description, String img, double DPL) {
        this.model = model;
        this.color = color;
        this.description = description;
        this.img = img;
        this.DPL = DPL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getDPL() {
        return DPL;
    }

    public void setDPL(double DPL) {
        this.DPL = DPL;
    }
}