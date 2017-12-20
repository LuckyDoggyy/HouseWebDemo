package com.house.model;


import java.util.Date;

public class HouseInformation {

    private int id;
    private String title;
    private int area;
    private int price;
    private int floor;
    private int totalFloor;
    private String description;
    private int bedroom;
    private int livroom;
    private String community;
    private String address;
    private String buildYear;
    private String name;
    private String phone;
    private Date pubTime;

    public HouseInformation(int id, String title, int area, int price, int floor, int totalFloor, String description, int bedroom, int livroom, String community, String address, String buildYear, String name, String phone, Date pubTime) {
        this.id = id;
        this.title = title;
        this.area = area;
        this.price = price;
        this.floor = floor;
        this.totalFloor = totalFloor;
        this.description = description;
        this.bedroom = bedroom;
        this.livroom = livroom;
        this.community = community;
        this.address = address;
        this.buildYear = buildYear;
        this.name = name;
        this.phone = phone;
        this.pubTime = pubTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}
