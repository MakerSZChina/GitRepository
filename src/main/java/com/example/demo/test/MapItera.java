package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapItera {

    public static void main(String[] args) {
        Map<String,Object>  map = new HashMap<String,Object>();
        Set<Map.Entry<String,Object>> set=map.entrySet();
        for(Map.Entry<String,Object> mape:set){
            System.out.println(mape.getKey()+","+mape.getValue());
        }

    }
}
