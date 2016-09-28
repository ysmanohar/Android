package com.example.mannu.inclass07;

/**
 * Created by mannu on 6/16/2016.
 */
public class App {
    String id;
    String appTitle, devName, url, smallImage, largeImage;
    String price,category,date;
    boolean status;
    String URL;
    int bookmark;

    public App(String id, String appTitle, String devName,String price, String category, String date, boolean status, String URL) {
        this.id = id;
        this.appTitle = appTitle;
        this.devName = devName;
        this.url = url;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.price = price;
        this.category = category;
        this.date = date;
        this.status = status;
        this.URL = URL;
        bookmark=R.drawable.grey;
    }

    public App(){
        bookmark=R.drawable.grey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getDevName() {
        return devName;
    }

    public boolean getStatus(){
        return true;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
