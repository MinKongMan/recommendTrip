package com.example.recommendtrip.web;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import com.example.recommendtrip.service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class restController {
    @GetMapping("/api/v1/get")
    public String go(){
        return "ㅗㅑ";
    }
    private final service service;
    @GetMapping("/api/v1")
    public addressLocalResponse find(@RequestParam String query){
        return service.searchLocal(query);
    }

    @GetMapping("/api/v1/find")
    public List<Address> find_all(){
        return service.find_all();
    }
}
