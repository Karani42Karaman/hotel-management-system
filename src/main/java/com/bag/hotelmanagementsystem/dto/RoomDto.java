package com.bag.hotelmanagementsystem.dto;

import lombok.Data;

import javax.persistence.*;


public class RoomDto {


    private Long roomNumber;

    private String description;

    private Long roomCount;

    private Long price;

    private Byte[] roomImage;


}
