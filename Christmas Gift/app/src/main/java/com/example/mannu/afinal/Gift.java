package com.example.mannu.afinal;

/**
 * Created by mannu on 6/28/2016.
 */
public class Gift {
    String gift,url,price;

    public Gift(String gift, String url, String price) {
        this.gift = gift;
        this.url = url;
        this.price = price;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
