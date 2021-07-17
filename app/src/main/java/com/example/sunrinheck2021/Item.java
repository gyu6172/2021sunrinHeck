package com.example.sunrinheck2021;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Item {
    Drawable backgroundImg;
    String name;
    int price;

    public Drawable getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(Drawable backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(Drawable backgroundImg, String name, int price) {
        this.backgroundImg = backgroundImg;
        this.name = name;
        this.price = price;
    }
}
