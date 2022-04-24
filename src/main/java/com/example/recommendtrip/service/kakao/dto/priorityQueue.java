package com.example.recommendtrip.service.kakao.dto;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.findDistance;

import java.util.LinkedList;

public class priorityQueue implements Comparable<priorityQueue>{
    int distance;
    LinkedList<Address> list;
    public priorityQueue(int distance, LinkedList<Address> list){
        this.distance = distance;
        this.list = list;
    }
    @Override
    public int compareTo(priorityQueue o) {
        return this.distance-o.distance;
    }
}
