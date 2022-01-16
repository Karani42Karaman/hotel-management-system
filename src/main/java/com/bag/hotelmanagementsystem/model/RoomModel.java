package com.bag.hotelmanagementsystem.model;



import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="room")
public class RoomModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "roomnumber",nullable=false)
    private Long roomNumber;

    @Column(name = "isReseved",nullable=false)
    private boolean isReseved;


    @Column(name = "description",nullable=false)
    private String description;

    @Column(name = "roomcount",nullable = false)
    private Long roomCount;

    @Column(name = "price",nullable = false)
    private Long price;

    @Lob
    @Column(name = "image")
    private Byte[] roomImage;




}
