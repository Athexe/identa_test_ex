package com.identa.spring.identa_test_ex.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="description")
    private String description;
    @Column(name="timestamp")
    private Date date;
    @Column(name="status")
    private String status;
    @Column(name="totalcost")
    private int totalcost;
    @Column(name="togo")
    private boolean togo;

    public Order(String username, String description, OffsetDateTime offsetDateTime, String status, int totalcost, boolean togo) {
        this.username = username;
        this.description = description;
        this.date = Date.from(offsetDateTime.toInstant());
        this.status = status;
        this.totalcost = totalcost;
        this.togo = togo;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return date;
    }

    public void setTimestamp(OffsetDateTime offsetDateTime) {
        this.date = Date.from(offsetDateTime.toInstant());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }

    public boolean isTogo() {
        return togo;
    }

    public void setTogo(boolean togo) {
        this.togo = togo;
    }
}