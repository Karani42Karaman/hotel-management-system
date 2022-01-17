package com.bag.hotelmanagementsystem.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="reservation")
public class ReservationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable=false)
    private String name;

    @Column(name = "surname",nullable=false)
    private String surName;

    @Column(name = "email",nullable=false)
    private String email;


    @Column(name = "tcnumber",nullable=false)
    private Long tcNumber;

    @Column(name = "telephonenumber",nullable=false)
    private Long telephoneNumber;


    @Column(name = "roomno",nullable=false)
    private Long roomNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureTime;



}
