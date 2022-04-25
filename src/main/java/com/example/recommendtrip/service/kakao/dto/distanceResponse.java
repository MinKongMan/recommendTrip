package com.example.recommendtrip.service.kakao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class distanceResponse {
    private List<item> routes;

    @Builder
    public distanceResponse(List<item> routes){
        this.routes = routes;
    }

    @NoArgsConstructor
    @Getter
    public static class item{
        private Map<String,Object> summary;
        @Builder
        public item(Map<String,Object> routes){
            this.summary = routes;
        }
    }
}
