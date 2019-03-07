package com.example.a21759217.appexpmdmt2_ricardomartinez.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skin {
    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rarity")
    @Expose
    private String rarity;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

}
