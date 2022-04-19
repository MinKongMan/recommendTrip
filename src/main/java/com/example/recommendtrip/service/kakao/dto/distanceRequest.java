package com.example.recommendtrip.service.kakao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class distanceRequest {
    private final String priority = "TIME";

    public MultiValueMap<String,String> toMultiValueMap(String origin, String destination) {
        var map = new LinkedMultiValueMap<String, String>();
        map.add("origin", origin);
        map.add("destination", destination);
        map.add("priority",priority);
        return map;
    }
}
