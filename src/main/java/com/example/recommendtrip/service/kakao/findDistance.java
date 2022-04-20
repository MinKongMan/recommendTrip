package com.example.recommendtrip.service.kakao;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.distanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class findDistance {
    int min = Integer.MAX_VALUE;
    int size = 0;
    int[][] array;
    int[] destination;
    boolean[] marked;
    private final kakaoClient kakaoClient;

    public int find(List<Address> list){
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

        backTracking(1);
        return min;
    }

    public void backTracking(int a){
        if(a==4){
            int val = 0;
            for(int i = 1; i<3; i++){
                val += array[destination[i]][destination[i+1]];
            }
            min = Math.min(min, val);
            return;
        }
        for(int i = 1; i<=size; i++){
            if(marked[i]) continue;
            marked[i] = true;
            destination[a] = i;
            backTracking(a+1);
            marked[i] = false;
        }
    }

}
