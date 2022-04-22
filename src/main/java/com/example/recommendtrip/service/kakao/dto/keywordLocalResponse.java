package com.example.recommendtrip.service.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class keywordLocalResponse {

    private List<item> documents;

    @Getter
    @NoArgsConstructor
    public static class item{
        private String place_name;
        private String address_name;
        private String x;
        private String y;
        item(String place_name, String address_name, String x, String y){
            this.place_name = place_name;
            this.x = x;
            this.y = y;
            this.address_name = address_name;
        }
    }
}
