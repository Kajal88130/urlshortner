package com.project.urlshortner.controller;

import com.project.urlshortner.exception.UrlShortnerException;
import com.project.urlshortner.model.RequestDto;
import com.project.urlshortner.model.ResponseDto;
import com.project.urlshortner.model.UrlShortnerEntity;


import com.project.urlshortner.repository.UrlShortnerRepository;
import com.project.urlshortner.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Logger;

@Controller
public class UrlShortnerController {

    private static final Logger log = Logger.getLogger(UrlShortnerController.class.getName());

    @Autowired
    UrlShortnerService urlShortnerService;

    @Autowired
    UrlShortnerRepository urlShortnerRepository;

    @PostMapping("/shorten")
    public ResponseEntity<Object> createShortUrl(@RequestBody RequestDto requestDto) {
        try {
            UrlShortnerEntity urlShortnerEntity = urlShortnerService.createShortUrl(requestDto.getOriginalUrl());
            ResponseDto response = new ResponseDto(
                    urlShortnerEntity.getId(),
                    urlShortnerEntity.getOriginalUrl(),
                    urlShortnerEntity.getShortUrl(),
                    urlShortnerEntity.getCreatedAt(),
                    urlShortnerEntity.getLastUpdatedAt()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UrlShortnerException.InvalidUrlException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/shorten/{shortUrl}")
    public ResponseEntity<String> deleteUrl(@PathVariable("shortUrl") String shortUrl) {
        urlShortnerService.deleteUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.OK).body("URL with Short Url: " +shortUrl +" deleted successfully");
    }
}
