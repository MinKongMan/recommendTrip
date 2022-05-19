package com.example.recommendtrip.web;

import com.example.recommendtrip.domain.Address;
import com.example.recommendtrip.service.kakao.dto.addressLocalResponse;
import com.example.recommendtrip.service.kakao.dto.keywordLocalResponse;
import com.example.recommendtrip.service.kakao.dto.priorityQueue;
import com.example.recommendtrip.service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class restController {
    public addressLocalResponse start = addressLocalResponse.builder().build();
    public addressLocalResponse end = null;
    private final service service;
    @GetMapping("/api/v1/address")
    public addressLocalResponse find(@RequestParam String query) {
        return service.searchLocal(query, 0);
    }

    @GetMapping("/api/v1/start")
    public addressLocalResponse find_start(@RequestParam String query) {
        start = service.searchLocal(query,1);
        return start;
    }

    @GetMapping("/api/v1/end")
    public addressLocalResponse find_end(@RequestParam String query) {
        end = service.searchLocal(query,1);
        return end;
    }

    @GetMapping("/api/v1/find")
    public List<Address> find_all() {
        return service.find_all();
    }

    @DeleteMapping("/api/v1/{id}")
    public Long delete(@PathVariable Long id){
        service.delete(id);
        return id;
    }

    @GetMapping("/api/v1/findDistance")
    public List<priorityQueue> findDistance() {
        return service.duration(find_all());
    }

    @GetMapping("/api/v1/keyword")
    public keywordLocalResponse find_keyword(String query){
        return service.findPlace(query);
    }

}
