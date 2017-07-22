package com.example.titivoradac.snaphelper;

/**
 * Created by titivorada.c on 7/21/2017.
 */

public class Player {
    private String name;
    private String club;

    public Player(String name, String club) {
        this.name = name;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
}
