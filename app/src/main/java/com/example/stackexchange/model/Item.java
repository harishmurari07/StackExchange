package com.example.stackexchange.model;

public class Item {

    public Owner owner;
    public int answer_count;
    public String title;

    public Owner getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public int getAnswer_count() {
        return answer_count;
    }
}
