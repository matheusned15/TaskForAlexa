package com.example.matheusned.taskForAlexa.url.service;


import com.example.matheusned.taskForAlexa.url.entity.UrlShortener;
import com.example.matheusned.taskForAlexa.url.exception.BadRequestException;
import com.example.matheusned.taskForAlexa.url.exception.NotFoundException;
import com.example.matheusned.taskForAlexa.url.repository.ShortenerUrlRepository;
import com.example.matheusned.taskForAlexa.url.request.RedirectCreationRequest;
import com.opsmatters.bitly.Bitly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UrlShortenerService {

    @Value("${BITLY_TOKEN}")
    String BITLY_TOKEN;
    private Bitly client;

    @PostConstruct
    public void setup(){
        client = new Bitly(BITLY_TOKEN);
    }

    @Autowired
    private ShortenerUrlRepository urlRepository;


    public Optional<UrlShortener> createRedirect(RedirectCreationRequest redirectCreationRequest) {
        if (urlRepository.existsByAlias(redirectCreationRequest.getAlias())) {
            throw new BadRequestException("Alias already exists");
        }
        System.out.println("Redirect Request " + redirectCreationRequest.toString());
        UrlShortener redirect = new UrlShortener(redirectCreationRequest.getUrl(), redirectCreationRequest.getAlias());

        UrlShortener postSaveRedirect = urlRepository.save(redirect);
        System.out.println("Redirect" + postSaveRedirect);


        return Optional.ofNullable(postSaveRedirect);
    }

    public UrlShortener getRedirect(String alias) {
        UrlShortener redirect = urlRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("Hey we don't have that alias ! Try making it"));
        return redirect;
    }

}
