package com.project.urlshortner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonFormat
public class ResponseDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;

    public ResponseDto(Long id, String originalUrl, String shortUrl, LocalDateTime createdAt, LocalDateTime lastUpdateAt) {
        this.id = id;
        this.originalUrl  = originalUrl;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
        this.lastUpdateAt = lastUpdateAt;
    }
}
