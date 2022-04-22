package com.example.recommendtrip.service.kakao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class keywordLocalRequest {
    private String query;
    private final String keyword = "AT4";

    @Builder
    keywordLocalRequest(String query){
        this.query = query;
    }
    public MultiValueMap<String, String> toMap(){
        var map = new LinkedMultiValueMap<String,String>();
        map.add("query",query);
        map.add("category_group_code",keyword);
        return map;
    }
}
