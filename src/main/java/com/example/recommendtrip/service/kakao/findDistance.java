package com.example.recommendtrip.service.kakao;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.distanceResponse;
import com.example.recommendtrip.service.kakao.dto.priorityQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

@Component
@RequiredArgsConstructor
public class findDistance {
    int min = Integer.MAX_VALUE;
    int size = 0;
    int[][] array;
    int[] destination;
    boolean[] marked;
    private final kakaoClient kakaoClient;
    PriorityQueue<priorityQueue> pq = new PriorityQueue<>();
    LinkedList<priorityQueue> pq_list = new LinkedList<>();
    public List<priorityQueue> find(List<Address> list){
        size = list.size();
        array = new int[size+1][size+1];
        marked = new boolean[size+1];
        destination = new int[4];
        for(int i = 1; i<=size; i++){
            for(int j = i+1; j<=size; j++){
                distanceResponse time = kakaoClient.disRes(list.get(i-1), list.get(j-1));
                array[i][j] = (int)time.getRoutes().get(0).getSummary().get("duration");
                array[j][i] = array[i][j];
            }
        }

        backTracking(1,list);
        while(!pq.isEmpty()){
            pq_list.add(pq.poll());
        }
        return pq_list;
    }

    public void backTracking(int a, List<Address> list){
        if(a==4){
            int val = 0;
            for(int i = 1; i<3; i++){
                val += array[destination[i]][destination[i+1]];
            }
            LinkedList<Address> temp_list = new LinkedList<>();
            for(int i = 1; i<=3; i++){
                temp_list.add(list.get(destination[i]-1));
            }
            pq.add(new priorityQueue(val,temp_list));
            int k = pq.size();
            for(int i = 1; i<=k; i++){
                if(i>3) pq.poll();
            }
            return;
        }
        for(int i = 1; i<=size; i++){
            if(marked[i]) continue;
            marked[i] = true;
            destination[a] = i;
            backTracking(a+1,list);
            marked[i] = false;
        }
    }

}
