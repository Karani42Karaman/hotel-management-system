package com.bag.hotelmanagementsystem.dto;

import lombok.Data;

import javax.persistence.*;


public class RoomDto {


    public  int roomNumber;

    public  String description;

    public  int roomCount;

    public  int price;

    public Byte[] roomImage;


}
