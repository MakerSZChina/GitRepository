package com.example.demo.dubbo.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable{

    private String cityName;
    private String cityCode;
    private String cityAddr;
}
