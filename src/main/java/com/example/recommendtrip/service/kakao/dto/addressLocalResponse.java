package com.example.recommendtrip.service.kakao.dto;

import lombok.*;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class addressLocalResponse {

    private List<item_address> documents;
    @Builder
    public addressLocalResponse(List<item_address> documents){
        this.documents = documents;
    }

    @NoArgsConstructor
    @Getter
    public static class item_address{
        private Map<String,Object> address;
        private String address_name;
        private String x;
        private String y;

        @Builder
        public item_address(String address_name, String x, String y, Map<String,Object> address){
            this.address = address;
            this.x = x;
            this.y = y;
            this. address_name = address_name;
        }
    }

}
