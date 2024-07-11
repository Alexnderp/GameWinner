package com.generation.gameWinner.model;

import jakarta.persistence.*;

import java.util.Date;

public class Characteristics {
    private String developer;
    private String publisher;
    private String launch;
    private int classification;

    public java.lang.String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String  getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

}

