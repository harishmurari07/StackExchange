package com.example.stackexchange.model;

public class Owner {

    public int reputation;
    public int user_id;
    public String user_type;
    public String profile_image;
    public String display_name;
    private int accept_rate;

    public int getAccept_rate() {
        return accept_rate;
    }
}
