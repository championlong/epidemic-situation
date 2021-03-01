package com.outbreak.dto;

public class TagDataResult {
    private String name;
    private String text;
    private String color;
    private int sum;
    private int add;

    public TagDataResult(String name, String text, String color, int sum, int add) {
        this.name = name;
        this.text = text;
        this.color = color;
        this.sum = sum;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }
}
