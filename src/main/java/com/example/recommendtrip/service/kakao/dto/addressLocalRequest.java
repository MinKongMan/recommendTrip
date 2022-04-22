package com.example.recommendtrip.service.kakao.dto;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor
@Getter
public class addressLocalRequest {

    private String query;
    @Builder
    addressLocalRequest(String query){
        this.query = query;
    }

    public MultiValueMap<String,String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();
        map.add("query",query);

        return map;
    }
}
