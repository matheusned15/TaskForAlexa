package com.example.matheusned.taskForAlexa.url.repository;

import com.example.matheusned.taskForAlexa.url.entity.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenerUrlRepository extends JpaRepository<UrlShortener, Long> {

    Optional<UrlShortener> findByAlias(String alias);

    Boolean existsByAlias(String alias);
}
