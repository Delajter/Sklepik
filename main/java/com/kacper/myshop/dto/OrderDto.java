package com.kacper.myshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
}
