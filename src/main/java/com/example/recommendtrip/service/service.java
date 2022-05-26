package com.example.recommendtrip.service;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.domain.AddressInterface;
import com.example.recommendtrip.service.kakao.dto.*;
import com.example.recommendtrip.service.kakao.findDistance;
import com.example.recommendtrip.service.kakao.kakaoClient;
import com.example.recommendtrip.web.restController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class service {

    private final kakaoClient kakaoClient;
    private final AddressInterface addressInterface;
    private final findDistance findDistance;
    private boolean check = false;
    @Transactional
    public addressLocalResponse searchLocal(String query, int val) {
        if(check) return null; // 이 부분에 작업처리 중이니 기다리라는 문구 출력하면 됨
        check = true;
        addressLocalResponse res = kakaoClient.localRes(addressLocalRequest.builder().query(query).build());

        Address address;
        addressLocalResponse.item_address items;

        if(res.getDocuments().size()<1) return addressLocalResponse.builder().documents(null).build();

        List<Address> list = find_all();
        if(list!=null) {
            for (Address temp : list) {
                if (temp.getX().equals(res.getDocuments().get(0).getX()) &&
                        temp.getY().equals(res.getDocuments().get(0).getY())) {
                    System.out.println("같은 장소가 있습니다.");
                    return addressLocalResponse.builder().documents(null).build();
                }
            }
        }


        items = res.getDocuments().get(0);
        address = Address.builder()
                .address_name(items.getAddress_name())
                .x(items.getX())
                .y(items.getY())
                .build();

        if(val==1){
            return res;
        }


        addressInterface.save(address);
        check = false;
        return addressLocalResponse.builder()
                .documents(res.getDocuments())
                .build();
    }

    public List<Address> find_all() throws NullPointerException{
        List<Address> list = addressInterface.findAll();

        if(list.size()==0) return null;
        else return list;
    }

    @Transactional
    public void delete(Long id){
        System.out.println(id);
        Address item = addressInterface.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        addressInterface.delete(item);
        System.out.println(id);
    }

    public List<priorityQueue> duration(List<Address> list){
        return findDistance.find(list);
    }

    @Transactional
    public keywordLocalResponse findPlace(String query){
        keywordLocalResponse keywordLocalResponse = kakaoClient.keyRes(keywordLocalRequest.builder().query(query).build());
        if(keywordLocalResponse==null || keywordLocalResponse.getDocuments().size()<1) return null;
        var node = keywordLocalResponse.getDocuments().get(0);
        Address address = Address.builder().x(node.getX())
                .y(node.getY())
                .address_name(node.getAddress_name())
                .build();

        addressInterface.save(address);
        return keywordLocalResponse;
    }

}
