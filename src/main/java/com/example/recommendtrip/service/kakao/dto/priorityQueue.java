package com.example.recommendtrip.service.kakao.dto;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.findDistance;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@NoArgsConstructor
@Getter
public class priorityQueue implements Comparable<priorityQueue>{
    int distance;
    LinkedList<Address> list;
    @Builder
    public priorityQueue(int distance, LinkedList<Address> list){
        this.distance = distance;
        this.list = list;
    }
    @Override
    public int compareTo(priorityQueue o) {
        return o.distance-this.distance;
    }
}
