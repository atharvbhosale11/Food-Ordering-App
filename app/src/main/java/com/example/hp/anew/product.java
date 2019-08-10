package com.example.hp.anew;

public class product
{
    private String title;
    private int price;
    private int image;

    public product(String title, int price, int image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
