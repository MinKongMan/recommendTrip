package com.example.recommendtrip.service;

import com.example.recommendtrip.service.kakao.dto.addressLocalRequest;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import com.example.recommendtrip.service.kakao.kakaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class service {

    private final kakaoClient kakaoClient;

    public addressLocalResponse searchLocal(String query){
        addressLocalRequest request = new addressLocalRequest();
        request.setQuery(query);
        addressLocalResponse res = kakaoClient.localRes(request);

        return addressLocalResponse.builder()
                .documents(res.getDocuments())
                .build();
    }
}
