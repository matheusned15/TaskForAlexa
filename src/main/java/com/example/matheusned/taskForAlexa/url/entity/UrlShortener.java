package com.example.matheusned.taskForAlexa.url.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UrlShortener implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "originalUrl")
    private String originalUrl;

    @Column(unique = true, nullable = false)
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public UrlShortener() {

    }

    public UrlShortener(String originalUrl, String alias) {
        this.originalUrl = originalUrl;
        this.alias = alias;
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

    @Override
    public String toString() {
        return "Redirect{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", url='" + originalUrl + '\'' +
                '}';
    }
}
