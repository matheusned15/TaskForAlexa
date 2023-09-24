package com.example.matheusned.taskForAlexa.url.controller;


import com.example.matheusned.taskForAlexa.url.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;


    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody String originalUrl){
        String shortUrl = service.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl, HttpServletResponse response) {
        return null;
    }
}
