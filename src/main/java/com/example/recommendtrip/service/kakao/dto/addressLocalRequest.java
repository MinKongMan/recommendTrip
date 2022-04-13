package com.example.recommendtrip.service.kakao.dto;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class addressLocalRequest {

    private String query;



    public MultiValueMap<String,String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();
        map.add("query",query);

        return map;
    }
}
