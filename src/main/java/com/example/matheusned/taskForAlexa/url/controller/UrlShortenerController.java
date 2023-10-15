package com.example.matheusned.taskForAlexa.url.controller;


import com.example.matheusned.taskForAlexa.url.entity.UrlShortener;
import com.example.matheusned.taskForAlexa.url.request.RedirectCreationRequest;
import com.example.matheusned.taskForAlexa.url.service.UrlShortenerService;
import com.opsmatters.bitly.api.services.BitlyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@Slf4j
@RequestMapping("/api/url")
public class UrlShortenerController {

    private UrlShortenerService service;

    @Autowired
    public UrlShortenerController(UrlShortenerService redirectService) { this.service = redirectService; }

    @GetMapping("/{alias}")
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException {
        UrlShortener redirect = service.getRedirect(alias);
        System.out.println("We're redirecting here!" + redirect);
        URI uri = new URI(redirect.getOriginalUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }

    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectCreationRequest redirectCreationRequest) {
        return ResponseEntity.ok(service.createRedirect(redirectCreationRequest));
    }

//    @RequestMapping(method=RequestMethod.POST)
//    public ResponseEntity<Void> insert(@RequestBody UrlShortener obj) {
//        obj = service.insert(obj);
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{shortURL}").buildAndExpand(obj.getShortUrl()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
}
