package com.bag.hotelmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="reservation")
public class ReservationModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "name",nullable=false)
    private String name;

    @Column(name = "surname",nullable=false)
    private String surName;

    @Column(name = "email",nullable=false)
    private String email;

    @Column(name = "password",nullable=false)
    private String password;

    @Column(name = "tcnumber",nullable=false)
    private String tcNumber;

    @Column(name = "telephonenumber",nullable=false)
    private String telephoneNumber;


    @Column(name = "roomno",nullable=false)
    private String roomNo;

    @Temporal(TemporalType.DATE)
    private Date arrivalTime;

    @Temporal(TemporalType.DATE)
    private Date departureTime;



}
