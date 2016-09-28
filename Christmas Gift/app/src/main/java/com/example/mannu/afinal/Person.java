package com.example.mannu.afinal;

/**
 * Created by mannu on 6/28/2016.
 */
public class Person {
    String name,budget,amountspent,giftsbought;;

    public Person(String name, String budget, String amountspent, String giftsbought) {
        this.name = name;
        this.budget = budget;
        this.amountspent = amountspent;
        this.giftsbought = giftsbought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getAmountspent() {
        return amountspent;
    }

    public void setAmountspent(String amountspent) {
        this.amountspent = amountspent;
    }

    public String getGiftsbought() {
        return giftsbought;
    }

    public void setGiftsbought(String giftsbought) {
        this.giftsbought = giftsbought;
    }
}
