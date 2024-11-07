package com.runaumov.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
