package com.project.urlshortner.model;


import lombok.Data;

@Data
public class RequestDto {
    private String originalUrl;

    public RequestDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
