package com.example.recommendtrip.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address_name;

    @Column
    private String x;

    @Column
    private String y;

    @Builder
    public Address (String address_name, String x, String y){
        this.address_name = address_name;
        this.x = x;
        this.y = y;
    }

    public Address toEntity(){
        return new Address();
    }
}
