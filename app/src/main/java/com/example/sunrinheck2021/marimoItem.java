package com.example.sunrinheck2021;

public class marimoItem {
    String name;
    String time;
    double diameter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public marimoItem(String name, String time, double diameter) {
        this.name = name;
        this.time = time;
        this.diameter = diameter;
    }
}
