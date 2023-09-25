package com.example.matheusned.taskForAlexa.url.entity;



import javax.persistence.*;

@Entity
public class UrlShortener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "originalUrl")
    private String originalUrl;
    @Column(name = "shortUrl")
    private String shortUrl;

    public UrlShortener(){

    }

    public UrlShortener(Long id, String originalUrl, String shortUrl){
        super();
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
