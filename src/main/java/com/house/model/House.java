package com.house.model;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bedroom")
    private int bedroom;

    @Column(name = "livroom")
    private int livroom;

    @Column(name = "community")
    private String community;

    @Column(name = "address")
    private String address;

    @Column(name = "builfyear")
    private int buildYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getLivroom() {
        return livroom;
    }

    public void setLivroom(int livroom) {
        this.livroom = livroom;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }
}
