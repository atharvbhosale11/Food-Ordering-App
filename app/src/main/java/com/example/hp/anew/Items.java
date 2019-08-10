package com.example.hp.anew;

public class Items
{
    String name,img;
    int price;
    public Items()
    {

    }

    public Items(String name, String img, int price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public int getPrice() {
        return price;
    }
}
