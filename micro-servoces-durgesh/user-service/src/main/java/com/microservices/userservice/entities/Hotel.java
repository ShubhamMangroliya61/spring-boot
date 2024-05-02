package com.microservices.userservice.entities;

import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;
}
