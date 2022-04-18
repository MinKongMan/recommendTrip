package com.example.recommendtrip.web;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import com.example.recommendtrip.service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class restController {

    private final service service;
    @GetMapping("/api/v1")
    public addressLocalResponse find(@RequestParam String query){
        return service.searchLocal(query);
    }

    @GetMapping("/api/v1/find")
    public List<Address> find_all(){
        return service.find_all();
    }

    @DeleteMapping("/api/v1/{id}")
    public Long delete(@PathVariable Long id){
        service.delete(id);
        return id;
    }


}
