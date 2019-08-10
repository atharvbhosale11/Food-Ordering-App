package com.example.hp.anew;

public class caart
{
    String name;
    int qty,price;;


    public caart()
    {

    }
    public caart(String name, int qty, int price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public int getPrice() {
        return price;
    }
}