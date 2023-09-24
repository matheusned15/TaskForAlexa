package com.example.matheusned.taskForAlexa.url.service;


import com.example.matheusned.taskForAlexa.url.repository.ShortenerUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    @Autowired
    private ShortenerUrlRepository urlRepository;

    public String shortenUrl(String originalUrl){

    }

}
