package com.example.recommendtrip.service.kakao;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.distanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class findDistance {
    int min = Integer.MIN_VALUE;
    int size = 0;
    int[][] array;

    private final kakaoClient kakaoClient;

    public int find(List<Address> list){
        size = list.size();
        array = new int[size+1][size+1];
        for(int i = 1; i<=size; i++){
            for(int j = i+1; j<=size; j++){
                System.out.println(list.get(i-1).getAddress_name()+" / "+list.get(j-1).getAddress_name());
                distanceResponse time = kakaoClient.disRes(list.get(i-1), list.get(j-1));
                System.out.println(time.getRoutes());
                array[i][j] = (int)time.getRoutes().get(0).getSummary().get("duration");
                System.out.println(i+" "+j+" "+array[i][j]);
            }
        }
        return min;
    }

//    public void backTracking(int a){
//        if(a==size+1){
//
//
//            return;
//        }
//        for()
//    }

}
