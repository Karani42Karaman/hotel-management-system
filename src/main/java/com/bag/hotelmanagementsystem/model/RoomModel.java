package com.bag.hotelmanagementsystem.model;



import javax.persistence.*;

@Entity
public class RoomModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "roomnumber",nullable=false)
    private Integer roomNumber;

    @Column(name = "isReseved",nullable=false)
    private boolean isReseved;


    @Column(name = "description",nullable=false)
    private String description;

    @Column(name = "roomcount",nullable = false)
    private int roomCount;

    @Column(name = "price",nullable = false)
    private int price;

    @Lob
    @Column(name = "image")
    private Byte[] roomImage;




}
