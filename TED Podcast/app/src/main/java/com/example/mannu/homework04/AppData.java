package com.example.mannu.homework04;

import java.io.Serializable;

/**
 * Created by mannu on 6/20/2016.
 */
public class AppData implements Serializable {

    String Title, Description, Date, Image_URL, Duration, Podcast;

    @Override
    public String toString() {
        return "AppData{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Date='" + Date + '\'' +
                ", Image_URL='" + Image_URL + '\'' +
                ", Duration='" + Duration + '\'' +
                ", Podcast='" + Podcast + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getPodcast() {
        return Podcast;
    }

    public void setPodcast(String podcast) {
        this.Podcast = podcast;
    }

}
