package com.project.urlshortner.service;


import com.project.urlshortner.controller.UrlShortnerController;
import com.project.urlshortner.exception.UrlShortnerException;
import com.project.urlshortner.model.UrlShortnerEntity;
import com.project.urlshortner.repository.UrlShortnerRepository;
import com.project.urlshortner.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class UrlShortnerService {

    @Autowired
    UrlShortnerRepository urlShortnerRepository;

    private static final Logger log= Logger.getLogger(UrlShortnerController.class.getName());


    private String generateShortCode() {
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(Constants.Short_URL_LENGTH.getLength());
        for (int i = 0; i < Constants.Short_URL_LENGTH.getLength(); i++) {
            shortUrl.append(Constants.CHARACTERS.getCharacters().charAt(random.nextInt(Constants.Short_URL_LENGTH.getLength())));
        }
        return shortUrl.toString();
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException exception) {
            throw new UrlShortnerException.InvalidUrlException("Mal formed URL");
        }
    }

    public UrlShortnerEntity createShortUrl(String originalUrl) {
//        if (!isValidUrl(originalUrl)) {
//            throw new UrlShortnerException.InvalidUrlException("Invalid URL: " + originalUrl);
//        }
        String shortUrl = generateShortCode();
        while (urlShortnerRepository.existsByShortUrl(shortUrl)) {
            shortUrl = generateShortCode();
        }
        UrlShortnerEntity urlShortnerEntity = new UrlShortnerEntity();
        urlShortnerEntity.setShortUrl(shortUrl);
        urlShortnerEntity.setOriginalUrl(originalUrl);
        urlShortnerEntity.setCreatedAt(LocalDateTime.now());
        urlShortnerEntity.setLastUpdatedAt(LocalDateTime.now());
        urlShortnerRepository.save(urlShortnerEntity);
        return urlShortnerEntity;
    }

    public void deleteUrl(String shorturl) {
        Optional<UrlShortnerEntity> optionalUrlShortnerEntity = urlShortnerRepository.findByShortUrl(shorturl);
        if(optionalUrlShortnerEntity.isPresent()) {
            UrlShortnerEntity urlShortnerEntity = optionalUrlShortnerEntity.get();
            urlShortnerRepository.delete(urlShortnerEntity);
        } else {
            log.info("Url doesnt exist");
        }
    }
}


