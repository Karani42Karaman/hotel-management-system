package com.bag.hotelmanagementsystem.model;



import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="room")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roomnumber",nullable=false)
    private int roomNumber;

    @Column(name = "isReserved",nullable=false)
    private boolean isReserved;

    @Column(name = "description",nullable=false)
    private String description;

    @Column(name = "roomcount",nullable = false)
    private int roomCount;

    @Column(name = "price",nullable = false)
    private int price;

    @Column(name = "roomtype",nullable = false)
    private String roomType;




}
