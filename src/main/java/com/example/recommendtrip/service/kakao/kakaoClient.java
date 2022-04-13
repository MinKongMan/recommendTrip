package com.example.recommendtrip.service.kakao;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.addressLocalRequest;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.*;

@Component
public class kakaoClient{
    @Value("${kakao.url.local}")
    private String kakaoUrl;

    @Value("${kakao.client.key}")
    private String key;

    public addressLocalResponse localRes(addressLocalRequest query){
        var uri = UriComponentsBuilder.fromUriString(kakaoUrl)
                .queryParams(query.toMultiValueMap())
                .build()
                .encode()
                .toUri();
        var httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization",key);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(httpHeaders);

        var resType = new ParameterizedTypeReference<addressLocalResponse>(){};

        var ResponseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, resType);


        return ResponseEntity.getBody();
    }
}
