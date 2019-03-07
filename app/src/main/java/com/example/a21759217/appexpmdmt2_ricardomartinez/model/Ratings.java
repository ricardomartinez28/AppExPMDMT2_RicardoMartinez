package com.example.a21759217.appexpmdmt2_ricardomartinez.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ratings {
    @SerializedName("avgStars")
    @Expose
    private Double avgStars;
    @SerializedName("totalPoints")
    @Expose
    private Integer totalPoints;
    @SerializedName("numberVotes")
    @Expose
    private Integer numberVotes;

    public Double getAvgStars() {
        return avgStars;
    }

    public void setAvgStars(Double avgStars) {
        this.avgStars = avgStars;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(Integer numberVotes) {
        this.numberVotes = numberVotes;
    }
}
