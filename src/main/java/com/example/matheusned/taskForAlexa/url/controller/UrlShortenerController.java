package com.example.matheusned.taskForAlexa.url.controller;


import com.example.matheusned.taskForAlexa.url.entity.UrlShortener;
import com.example.matheusned.taskForAlexa.url.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";

    @Autowired
    private UrlShortenerService service;


    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody String originalUrl) throws URISyntaxException {
        UrlShortener shortUrl = service.findByShortUrl(originalUrl);
            if(shortUrl == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            String redirect = shortUrl.getOriginalUrl();
        if (!redirect.substring(0, HTTP_PREFIX.length()).equals(HTTP_PREFIX) &&
                !redirect.substring(0, HTTPS_PREFIX.length()).equals(HTTPS_PREFIX)) {
            redirect = HTTP_PREFIX.concat(redirect);
        }
        URI uri = new URI(redirect);
        HttpHeaders http = new HttpHeaders();
        http.setLocation(uri);

        return new ResponseEntity<>(http, HttpStatus.SEE_OTHER);

    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UrlShortener obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{shortURL}").buildAndExpand(obj.getShortUrl()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
