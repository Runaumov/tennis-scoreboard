package com.runaumov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestMatchesDto {
    String name;
    int page;

    public RequestMatchesDto(String name) {
        this.name = name;
    }
}
