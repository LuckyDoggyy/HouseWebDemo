package com.house.model;



import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "house_info")
public class HouseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "area")
    private int area;

    @Column(name = "price")
    private int price;

    @Column(name = "floor")
    private int floor;

    @Column(name = "tfloor")
    private int totalFloor;

    @Column(name = "desc_id")
    private int descId;

    @Column(name = "broker_id")
    private int brokerId;

    @Column(name = "house_id")
    private int houseId;

    @Column(name = "pub_time")
    private Date pubTime;

    public HouseInfo(){}

    public HouseInfo(String title, int area, int price, int floor, int totalFloor, int descId, int brokerId, int houseId, Date pubTime) {
        this.title = title;
        this.area = area;
        this.price = price;
        this.floor = floor;
        this.totalFloor = totalFloor;
        this.descId = descId;
        this.brokerId = brokerId;
        this.houseId = houseId;
        this.pubTime = pubTime;
    }

    @Override
    public String toString(){
        return id + "," +
                title + "," +
                area + "," +
                price + "," +
                floor + "," +
                totalFloor + "," +
                descId + "," +
                brokerId + "," +
                houseId + "," +
                pubTime;

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

    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}
