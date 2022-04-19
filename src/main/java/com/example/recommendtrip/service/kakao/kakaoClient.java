package com.example.recommendtrip.service.kakao;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.addressLocalRequest;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import com.example.recommendtrip.service.kakao.dto.distanceRequest;
import com.example.recommendtrip.service.kakao.dto.distanceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.*;

@Component
public class kakaoClient{
    @Value("${kakao.url.local}")
    private String kakaoLocal;

    @Value("${kakao.client.key}")
    private String key;

    @Value("${kakao.url.distance}")
    private String kakaoDistance;

    public addressLocalResponse localRes(addressLocalRequest query){
        var uri = UriComponentsBuilder.fromUriString(kakaoLocal)
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

    public distanceResponse disRes(Address address_start, Address address_end){
        distanceRequest distanceRequest = new distanceRequest();
        MultiValueMap<String, String> map = distanceRequest.toMultiValueMap(address_start.getX()+","+address_start.getY(),
                address_end.getX()+","+address_end.getY());
        var uri = UriComponentsBuilder.fromUriString(kakaoDistance)
                .queryParams(map)
                .encode()
                .build()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",key);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        var resType = new ParameterizedTypeReference<distanceResponse>(){};

        var ResponseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, resType);
        System.out.println(ResponseEntity);
        return ResponseEntity.getBody();
    }
}
