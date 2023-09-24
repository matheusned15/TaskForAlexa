package com.example.matheusned.taskForAlexa.url.repository;

import com.example.matheusned.taskForAlexa.url.entity.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenerUrlRepository extends JpaRepository<UrlShortener, Long> {
    UrlShortener findByShortUrl(String shortUrl);
}
