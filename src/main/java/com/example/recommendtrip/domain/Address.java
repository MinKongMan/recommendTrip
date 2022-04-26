package com.example.recommendtrip.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

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

    @Column
    private String start_x;

    @Column
    private String start_y;

    @Column
    private String end_x;

    @Column
    private String end_y;

    @Builder
    public Address (String address_name, String x, String y, String start_x, String start_y, String end_x, String end_y){
        this.address_name = address_name;
        this.x = x;
        this.y = y;
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
    }

    public Address toEntity(){
        return new Address();
    }
}
