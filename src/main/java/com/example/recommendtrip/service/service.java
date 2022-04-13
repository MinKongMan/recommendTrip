package com.example.recommendtrip.service;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.domain.AddressInterface;
import com.example.recommendtrip.service.kakao.dto.addressLocalRequest;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import com.example.recommendtrip.service.kakao.kakaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class service {

    private final kakaoClient kakaoClient;
    private final AddressInterface addressInterface;

    @Transactional
    public addressLocalResponse searchLocal(String query){
        addressLocalRequest request = new addressLocalRequest();
        request.setQuery(query);
        addressLocalResponse res = kakaoClient.localRes(request);
        Address address;
        addressLocalResponse.item_address items;
        if(res.getDocuments().size()<1) return addressLocalResponse.builder().documents(null).build();

        items = res.getDocuments().get(0);
        address = Address.builder()
                .address_name(items.getAddress_name())
                .x(items.getX())
                .y(items.getY())
                .build();

        addressInterface.save(address);

        return addressLocalResponse.builder()
                .documents(res.getDocuments())
                .build();
    }

    public List<Address> find_all(){
        List<Address> list = addressInterface.findAll();
        if(list.size()==0) return null;
        else return list;

    }
}
