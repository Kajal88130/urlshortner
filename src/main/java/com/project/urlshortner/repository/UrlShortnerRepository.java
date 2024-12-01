package com.project.urlshortner.repository;

import com.project.urlshortner.model.UrlShortnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortnerRepository extends JpaRepository<UrlShortnerEntity, Long> {

    Optional<UrlShortnerEntity> findById(Long id);

    boolean existsByShortUrl(String shorturl);

    @Query("SELECT u FROM UrlShortnerEntity u WHERE u.shortUrl = :shortUrl")
    Optional<UrlShortnerEntity> findByShortUrl(String shortUrl);

    Optional<UrlShortnerEntity> findByOriginalUrl(String originalUrl);


}

