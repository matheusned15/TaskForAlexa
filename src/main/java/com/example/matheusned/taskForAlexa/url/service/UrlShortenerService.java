package com.example.matheusned.taskForAlexa.url.service;


import com.example.matheusned.taskForAlexa.url.entity.UrlShortener;
import com.example.matheusned.taskForAlexa.url.repository.ShortenerUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerService {

    @Autowired
    private ShortenerUrlRepository urlRepository;

    private IDConverter converter = IDConverter.getInstance();

    public UrlShortener findByShortUrl(String originalUrl){
        Optional<UrlShortener> obj = urlRepository.findByShortUrl(originalUrl);
        return obj.orElse(null);
    }

    public UrlShortener insert(UrlShortener url){
        url.setId(null);
        url = urlRepository.save(url);
        url.setShortUrl(converter.toBase62(String.valueOf(url.getId())));
        return urlRepository.save(url);
    }

}
